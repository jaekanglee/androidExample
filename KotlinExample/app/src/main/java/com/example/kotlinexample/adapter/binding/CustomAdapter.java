package com.example.kotlinexample.adapter.binding;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter {



    @BindingAdapter("productGridAdapter")
    public static void productGridAdapter(RecyclerView recyclerView,RecyclerView.Adapter<?> adapter){

        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);


    }
}
