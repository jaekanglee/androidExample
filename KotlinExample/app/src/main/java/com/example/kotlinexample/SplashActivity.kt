package com.example.kotlinexample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.kotlinexample.databinding.ActivitySplashBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

class SplashActivity : BaseActivity<ActivitySplashBinding>(R.layout.activity_splash) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        compositable.add(
            Observable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data -> gotNextActivity() }
        )

    }

    fun gotNextActivity() {
        var intent = Intent(this, MainActivity::class.java)
        Toast.makeText(this,"헤이맨,",Toast.LENGTH_LONG).show()
        startActivity(intent)
    }

}
