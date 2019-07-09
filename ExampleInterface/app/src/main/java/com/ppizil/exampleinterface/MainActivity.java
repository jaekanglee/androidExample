package com.ppizil.exampleinterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.ppizil.exampleinterface.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyAdapter adapter;
    private ActivityMainBinding binding;
    private ClickCallbackListener callbackListener = new ClickCallbackListener() {
        @Override
        public void callBack(int pos) {
            Toast.makeText(MainActivity.this, pos + " 번째 아이템을 클릭했슴둥", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main);
        init();

    }

    public void init() {
        adapter = new RecyAdapter(); // 어댑터 생성

        List<String> items = new ArrayList<>();
        for (int i = 0; i < 30; i++) { //가상으로 아이템 만들어주는 포문
            items.add("테스트 영역 Index :" + i);
        }
        adapter.setItems(items);
        adapter.setCallbackListener(callbackListener);

        /*리사이클러뷰에 레이아웃 매니저 할당 및 어탭터 셋 */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
        binding.recyclerview.setAdapter(adapter);
    }
}
