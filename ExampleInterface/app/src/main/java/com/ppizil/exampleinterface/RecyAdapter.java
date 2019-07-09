package com.ppizil.exampleinterface;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ppizil.exampleinterface.databinding.ItemTextBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> items;
    private ClickCallbackListener callbackListener;

    public RecyAdapter() {
        items = new ArrayList<>();
    }

    public void setItems(List<String> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    //메인액티비티에서 전달 받은 콜백메서드를 set 하는 메서드
    public void setCallbackListener(ClickCallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTextBinding binding = ItemTextBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            String item = items.get(position);
            /*해당 아이템과 callbackListener를 바인드 된 뷰홀더에 전달한다.*/
            ((ItemViewHolder) holder).bind(item, callbackListener);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemTextBinding binding;
        private ClickCallbackListener callbackListener;

        public ItemViewHolder(ItemTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            setListener(); //부모 뷰그룹에 클릭리스너를 등록
        }

        public void setListener() {
            binding.rootView.setOnClickListener(this);
        }

        public void bind(String item, ClickCallbackListener callbackListener) {
            binding.textTitle.setText(item);// 전달받은 String을 setText
            this.callbackListener = callbackListener; //전달받은 콜백을 해당 뷰홀더의 멤버로 가지고있음.

        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            /*클릭 후 메인액티비티에서 구현되어 전달 된 콜백 메서드를 호출하여 인자로 클릭 포지션을 담았다.*/
            callbackListener.callBack(getAdapterPosition());
        }
    }
}
