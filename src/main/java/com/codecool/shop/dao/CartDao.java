package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;
import com.codecool.shop.model.User;

public interface CartDao  {
    void create(User user);
    Cart getCartByUserId(int userId);
    void addProductToCart(int productId,Cart cart);
    void increaseProductQuantity(int productId, Cart cart);
    void decreaseProductQuantity(int productId, Cart cart);
    void deleteProductFromCart(int productId, Cart cart);
}
