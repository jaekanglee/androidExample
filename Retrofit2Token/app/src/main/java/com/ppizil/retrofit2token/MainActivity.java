package com.ppizil.retrofit2token;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MyApplication.AuthenticationListener authenticationListener = new MyApplication.AuthenticationListener() {
        @Override
        public void onUserLoggedOut() {
            Toast.makeText(MainActivity.this, "로그아웃 합니다", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
