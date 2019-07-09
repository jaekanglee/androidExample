package com.ppizil.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ppizil.myapplication.databinding.ItemViewBinding

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        return 50
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  ItemViewHolder(binding)
    }


    class ItemViewHolder(binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind() {

        }

    }
}