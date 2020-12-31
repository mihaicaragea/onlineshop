package com.codecool.shop.model;

public class CartItem {
    private int productId;
    private String name;
    private float defaultPrice;
    private String defaultCurrency;
    private int quantity;



    public CartItem(int ID, String name, float defaultPrice, String defaultCurrency, int quantity){
        this.productId =ID;
        this.name = name;
        this.defaultPrice = defaultPrice;
        this.defaultCurrency = defaultCurrency;
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

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }


}
