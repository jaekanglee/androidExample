package com.example.spannedgridlayouttest

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager

import android.os.Bundle

import com.arasthel.spannedgridlayoutmanager.SpanSize
import com.arasthel.spannedgridlayoutmanager.SpannedGridLayoutManager
import com.example.spannedgridlayouttest.databinding.ActivityMainBinding

import java.util.function.Function

class MainActivity : AppCompatActivity() {


    private lateinit  var binding: ActivityMainBinding
    private var gridAdapter: GridAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        gridAdapter = GridAdapter()

        val spannedGridLayoutManager = SpannedGridLayoutManager(SpannedGridLayoutManager.Orientation.VERTICAL, 3)
        spannedGridLayoutManager.itemOrderIsStable = true


        spannedGridLayoutManager.spanSizeLookup = SpannedGridLayoutManager.SpanSizeLookup { position ->
            SpanSize(1, 1)
        }

        binding.recyclerview.layoutManager = spannedGridLayoutManager
        binding.recyclerview.adapter = gridAdapter
    }

}
