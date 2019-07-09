package com.ppizil.stepcounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private StepService stepService;
    boolean isService = false; // 서비스 중인 확인용

    private TextView textCount, statusService;
    private Button startBtn, endBtn;
    private Intent intent;

    private StepCallback stepCallback = new StepCallback() {
        @Override
        public void onStepCallback(int step) {
            textCount.setText("" + step);
        }

        @Override
        public void onUnbindService() {
            isService = false;
            statusService.setText("해제됨");
            Toast.makeText(MainActivity.this, "디스바인딩", Toast.LENGTH_SHORT).show();
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivity.this, "예스바인딩", Toast.LENGTH_SHORT).show();
            StepService.MyBinder mb = (StepService.MyBinder) service;
            stepService = mb.getService(); //
            stepService.setCallback(stepCallback);
            isService = true;
            statusService.setText("연결됨");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isService = false;
            statusService.setText("해제됨");
            Toast.makeText(MainActivity.this, "디스바인딩", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stepService = new StepService();
        startBtn = findViewById(R.id.startBtn);
        endBtn = findViewById(R.id.endBtn);
        textCount = findViewById(R.id.textCount);
        statusService = findViewById(R.id.textStatusService);
        setListener();

    }
    public void setListener() {
        startBtn.setOnClickListener(this);
        endBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.startBtn:
                intent = new Intent(this, StepService.class);
                startService(intent);
                bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.endBtn:
                try {
                    stopService(intent);
                    unbindService(serviceConnection);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        unbindService(serviceConnection);
    }
}
