package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;

public interface CartDao  {
    void create(Cart cart);
    Cart getCartByUserId(int userId);
    void addProductToCart(int productId,Cart cart);
    void increaseProductQuantity(int productId,Cart cart);
    void decreaseProductQuantity(int productId,Cart cart);
}
