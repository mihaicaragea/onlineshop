package com.codecool.shop.model;

public class CartItem {
    private int productId;
    private String name;
    private final float defaultPrice;
    private  int quantity;



    public CartItem(int ID, String name, float defaultPrice, String defaultCurrency, int quantity){
        this.productId =ID;
        this.name = name;
        this.defaultPrice = defaultPrice;
        this.quantity = quantity;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productcId) {
        this.productId = productcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDefaultPrice() {
        return defaultPrice;
    }

    public int getQuantity() {
        return quantity;
    }




}
