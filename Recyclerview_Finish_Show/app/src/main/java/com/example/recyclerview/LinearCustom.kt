package com.example.recyclerview

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LinearCustom(context : Context,callback :CallBackCustom) : LinearLayoutManager(context) {

    private val  callback : CallBackCustom =  callback

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)

        Log.e("차일드 포지션","${findLastVisibleItemPosition()}")

    }

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)

        Log.e("컴플리트 포지션","${findLastVisibleItemPosition()}")
        callback.callBack(48)
    }
}