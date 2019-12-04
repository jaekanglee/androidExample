package com.example.globalerror;

import android.app.Application;

import androidx.annotation.NonNull;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        setCrashHandler();
    }

    public void setCrashHandler(){
        final Thread.UncaughtExceptionHandler defaultExceptionHandler = new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {

            }
        };

        Thread.UncaughtExceptionHandler fabricExceptionHandler =new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {

            }
        };
        Thread.setDefaultUncaughtExceptionHandler(new HeyDealerExceptionHandler(this,defaultExceptionHandler,fabricExceptionHandler));

    }

}
