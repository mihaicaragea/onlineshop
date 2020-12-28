package com.codecool.shop.dao.DBImplimentation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDaoDB implements CartDao {
    private final DataSource dataSource;

    public static CartDaoDB getInstance() {
        return instance;
    }

    private static CartDaoDB instance;

    public CartDaoDB(DataSource dataSource){
        this.dataSource = dataSource;
        instance=this;
    }

    @Override
    public void create(Cart cart) {

    }

    @Override
    public Cart getCartByUserId(int userId) {
        ArrayList<CartItem> listOfItems = new ArrayList<>();
        Cart cart = new Cart(userId);

        try(Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name,default_price,default_currency,quantity, cart_id  FROM products\n" +
                    "JOIN cart  ON user_id = ?\n" +
                    "JOIN cart_item on cart.id = cart_item.cart_id\n" +
                    "where products.id = cart_item.product_id";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                CartItem cart_item = new CartItem(rs.getString(1),rs.getFloat(2),rs.getString(3),rs.getInt(4));
                listOfItems.add(cart_item);
            }
            cart.setProducts(listOfItems);
            return cart;

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while trying to find a user. " + throwable, throwable);
        }

    }

}
