package com.example.spannedgridlayouttest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spannedgridlayouttest.databinding.ItemGridBinding;

public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemGridBinding binding = ItemGridBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder{
        private ItemGridBinding binding;

        public ItemViewHolder(ItemGridBinding binding) {
            super(binding.getRoot());
        }

        public void bind(int pos){


        }
    }
}
