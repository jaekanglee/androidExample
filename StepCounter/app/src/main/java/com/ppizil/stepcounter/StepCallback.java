package com.ppizil.stepcounter;

public interface StepCallback {
    void onStepCallback(int step); //서비스에서 스텝 변화 시 액티비티로 전달하는 콜백 함수
    void onUnbindService(); // 서비스 언바인드 시 액티비티로 전달하는 콜백 함수
}
