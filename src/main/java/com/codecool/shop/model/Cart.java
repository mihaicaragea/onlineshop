package com.codecool.shop.model;

import java.util.ArrayList;

public class Cart {
    private int id;
    private int userId;
    private ArrayList products = new ArrayList();

    public Cart (int userId){
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ArrayList getProducts() {
        return products;
    }

    public void setProducts(ArrayList products) {
        this.products = products;
    }
}
