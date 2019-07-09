package com.ppizil.setvisiblehint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class ParentActivity : AppCompatActivity() {

    lateinit var tablayout :TabLayout
    lateinit var viewpager : ViewPager
    lateinit var parentAdapter : ParentAdapter






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parent)

        tablayout  = findViewById(R.id.tablayout)
        viewpager = findViewById(R.id.viewpager)

        parentAdapter = ParentAdapter(supportFragmentManager)

        viewpager?.adapter =parentAdapter

        tablayout?.setupWithViewPager(viewpager)

    }
}
