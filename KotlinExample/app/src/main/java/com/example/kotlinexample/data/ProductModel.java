package com.example.kotlinexample.data;

import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private List<ProductEntity> items;


    public ProductModel() {
        items = new ArrayList<>();
    }


    public List<ProductEntity> getItems() {
        return items;
    }

    public void setItems(List<ProductEntity> items) {
        this.items = items;
    }
}
