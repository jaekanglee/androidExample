package com.example.kotlinexample.base;

import androidx.lifecycle.ViewModel;

public abstract class BaseViewModel extends ViewModel {

    public abstract void onCreate();
    public abstract void onResume();
    public abstract void onDestroy();
    public abstract void onStart();
    public abstract void onStop();

}
