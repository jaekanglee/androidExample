package com.example.croptestproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.croptestproject.utils.BitmapLoadUtils;
import com.example.croptestproject.utils.ItemClickCallback;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Uri> itemList;
    private ItemClickCallback callback;

    public ImageAdapter() {
        this.itemList = new ArrayList<>();
    }

    public void setCallback(ItemClickCallback callback) {
        this.callback = callback;
    }

    public void setList(List<Uri> items) {
        itemList = items;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            ((ItemViewHolder) holder).setBitmap(position);
        }

    }

    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View view;
        private ImageView imageView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.view = itemView;
            imageView = view.findViewById(R.id.imageView);
            imageView.setOnClickListener(this);
        }

        public void setBitmap(int pos) {
            Glide.with(view.getContext()).load(itemList.get(pos).getPath()).thumbnail(0.1f).into(imageView);
            //   mEditButton ?.isEnabled = true
        }


        @Override
        public void onClick(View v) {
            callback.Click(getAdapterPosition());
        }
    }
}
