package com.example.kotlinexample

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.kotlinexample.databinding.ActivityMainBinding
import com.example.kotlinexample.viewmodel.ProductListViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {


    lateinit var  viewmodel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.textSample.text="야이 그지같은 코틀린"

        Toast.makeText(this,"메인액티비티",Toast.LENGTH_LONG).show()
        initActivity()
    }

    fun initActivity(){
        viewmodel = ViewModelProviders.of(this).get(ProductListViewModel::class.java)
        viewmodel.onCreate()

        binding.lifecycleOwner= this
        binding.viewmodel= viewmodel

    }


}