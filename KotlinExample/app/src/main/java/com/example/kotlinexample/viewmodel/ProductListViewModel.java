package com.example.kotlinexample.viewmodel;

import com.example.kotlinexample.adapter.ProductListAdapter;
import com.example.kotlinexample.base.BaseViewModel;
import com.example.kotlinexample.data.ProductModel;

public class ProductListViewModel extends BaseViewModel {

    private ProductModel model;
    public ProductListAdapter adapter;

    @Override
    public void onCreate() {
        model = new ProductModel();
        adapter = new ProductListAdapter(this);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }


    public ProductModel getModel() {
        return model;
    }

    public void setModel(ProductModel model) {
        this.model = model;
    }

    public String getProductName(int pos){
        //getModel().getItems().get(pos).getTitle();
        return ""+pos;
    }

    public String getProductUrl(int pos){
        return  "naver :"+pos;
    }
}
