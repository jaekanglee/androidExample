package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemRecyclerviewBinding

class RecyAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var binding: ItemRecyclerviewBinding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return 50;
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ItemViewHolder).bind(position)
    }


    class ItemViewHolder(binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        private val binding: ItemRecyclerviewBinding = binding

        fun bind(pos: Int) {
            binding.pos = pos;
            binding.text.text="$pos ㄴㅇㄹㄴㅇㄹㄴㄹㅇㄴㅇㄹㄴㅇㄹ"
            binding.executePendingBindings()
        }


    }
}