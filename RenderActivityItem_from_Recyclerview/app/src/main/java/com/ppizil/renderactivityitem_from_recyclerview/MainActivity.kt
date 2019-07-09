package com.ppizil.renderactivityitem_from_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ppizil.renderactivityitem_from_recyclerview.databinding.ActivityMainBinding
import java.util.ArrayList

class MainActivity : AppCompatActivity() {


    var binding: ActivityMainBinding? = null
    lateinit var items: MutableList<ItemEntity>
    lateinit var adapter: BoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init();
    }

    fun init() {
        items = ArrayList()
        for (i in 0..19) {
            val item = ItemEntity()
            item.title = "$i 번째 글 입니다."
            item.contents = "안녕하세요. 개발자 삐질입니다.\n 오늘은 리사이클러뷰 아이템을 액티비티에 전달하는 방법에 대해 알아보도록 하겠습니다."
            item.subTitle = item.contents?.indexOf("\n")?.let { item.contents?.substring(0, it) }
            item.date = System.currentTimeMillis().toString()
            items.add(item)
        }

        adapter = BoardAdapter(this)
        adapter.items = items
        var linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation= RecyclerView.VERTICAL
        binding?.reyclerView?.layoutManager = linearLayoutManager
        binding?.reyclerView?.adapter = adapter
    }
}
