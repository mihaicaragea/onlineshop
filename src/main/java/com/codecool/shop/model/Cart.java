package com.codecool.shop.model;

import java.util.ArrayList;

public class Cart {
    private int id;
    private int userId;
    private ArrayList<CartItem> products = new ArrayList();
    private float totalPrice;
    private int numberOfItems;


    public int getNumberOfItems() {

        for(CartItem item:products){
            numberOfItems= numberOfItems+item.getQuantity();
        }
        return numberOfItems;
    }

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

    public ArrayList<CartItem> getProducts() {
        return products;
    }

    public void setProducts(ArrayList products) {
        this.products = products;
    }

    public float getTotalPrice(){
        for(CartItem item:products){
            totalPrice=totalPrice+(item.getQuantity()*item.getDefaultPrice());
        }
        return totalPrice;
    }
}
