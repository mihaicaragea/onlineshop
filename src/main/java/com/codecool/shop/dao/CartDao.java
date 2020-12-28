package com.codecool.shop.dao;

import com.codecool.shop.model.Cart;

public interface CartDao  {
    void create(Cart cart);
    Cart getCartByUserId(int userId);
}
