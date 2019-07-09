package com.ppizil.renderactivityitem_from_recyclerview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ppizil.renderactivityitem_from_recyclerview.databinding.ActivityItemDetailBinding

class ItemDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityItemDetailBinding
    lateinit var item: ItemEntity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_detail)
        init();
    }

    fun init() {
        item = intent.extras.get("item") as ItemEntity
        binding.title.setText(item.title)
        binding.contents.setText(item.contents)
        binding.date.setText(item.date)
    }
}
