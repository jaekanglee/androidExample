package com.ppizil.renderactivityitem_from_recyclerview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.ppizil.renderactivityitem_from_recyclerview.databinding.ItemEntityBinding

import java.util.ArrayList

class BoardAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<ItemEntity>

    init {
        items = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemEntityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.bind(context, position, items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(private val binding: ItemEntityBinding)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private var context: Context? = null
        private var pos: Int = 0
        private var itemEntity: ItemEntity? = null

        init {
            init()
        }

        fun bind(context: Context, pos: Int, itemEntity: ItemEntity) {
            this.context = context
            this.pos = pos
            this.itemEntity = itemEntity
            binding.title.setText(itemEntity.title)
            binding.subTitle.setText(itemEntity.subTitle)
            binding.date.setText(itemEntity.date)
            binding.executePendingBindings()
        }

        fun init() {
            binding.viewGroup.setOnClickListener(this)
            binding.title.setClickable(false)
            binding.subTitle.setClickable(false)
            binding.date.setClickable(false)
        }

        override fun onClick(v: View) {
            val id = v.id
            when (id) {
                R.id.viewGroup -> {
                    val intent = Intent(context, ItemDetailActivity::class.java)
                    intent.putExtra("item", itemEntity)
                    context?.startActivity(intent)
                }
            }
        }
    }
}
