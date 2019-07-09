package com.ppizil.myapplication;

import android.os.Build;
import android.util.Log;

public class MakeLog {

    public static void Log_e(String tag, String msg) {
        if (BuildConfig.DEBUG) { //Debug
            Log.e(tag, msg);
        } else { //Release
            if(BuildConfig.IS_SHOW_LOG.equals("Y")) {
            }
            else{
                Log.e(tag, msg);
            }
        }
    }

    public static void Log_v(String tag, String msg) {
        Log.v(tag, msg);
    }

    public static void Log_i(String tag, String msg) {
        Log.i(tag, msg);
    }

    public static void Log_d(String tag, String msg) {
        Log.d(tag, msg);
    }

    public void log() {

        Log.e("Tag", "Message");
        Log.d("Tag", "Message");
        Log.i("Tag", "Message");
        Log.v("Tag", "Message");
    }
}
