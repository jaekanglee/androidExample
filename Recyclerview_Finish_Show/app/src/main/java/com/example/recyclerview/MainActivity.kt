package com.example.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main),CallBackCustom{
    override fun callBack(msg: Any) {
        binding.recyclerview.postDelayed(Runnable {
            binding.recyclerview.smoothScrollToPosition(msg as Int) },500)

    }

    val adapter = RecyAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var layoutManager = LinearLayoutManagerWithSmoothScroller(this)
        layoutManager.callback= this
        binding.recyclerview.layoutManager =layoutManager


//        binding.recyclerview.layoutManager = LinearCustom(this,this)

        binding.recyclerview.adapter = adapter
        adapter.notifyDataSetChanged()



    }
}
