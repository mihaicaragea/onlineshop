package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

public interface UserDao {

    void add(User user);
    User getByEmail(String email);
}
