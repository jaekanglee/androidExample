package com.example.kotlinexample.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kotlinexample.databinding.ItemHotDealBinding;
import com.example.kotlinexample.viewmodel.ProductListViewModel;

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ProductListViewModel viewModel;

    public ProductListAdapter(ProductListViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHotDealBinding binding = ItemHotDealBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ((ItemViewHolder) holder).bind(viewModel,position);
        }

    }

    @Override
    public int getItemCount() {
       // return viewModel.getModel().getItems().size();
        return 20;
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ItemHotDealBinding binding;

        public ItemViewHolder(ItemHotDealBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(ProductListViewModel viewModel,int pos){
            binding.setViewmodel(viewModel);
            binding.setPos(pos);
            binding.executePendingBindings();

        }
    }
}
