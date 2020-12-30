package com.codecool.shop.dao.DBImplimentation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
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
            String sql = "SELECT name,default_price,default_currency,quantity, cart_item.product_id, cart.id  FROM products\n" +
                    "JOIN cart  ON user_id = ?\n" +
                    "JOIN cart_item on cart.id = cart_item.cart_id\n" +
                    "where products.id = cart_item.product_id";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, userId);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                CartItem cart_item = new CartItem(rs.getInt(5), rs.getString(1),rs.getFloat(2),rs.getString(3),rs.getInt(4));
                listOfItems.add(cart_item);
                cart.setId(rs.getInt(6));
            }
            cart.setProducts(listOfItems);
            return cart;

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while trying to find a user. " + throwable, throwable);
        }

    }

    @Override
    public void addProductToCart(int productId, Cart cart) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cart_item(cart_id, quantity, product_id) VALUES (?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,cart.getId());
            st.setInt(2,1);
            st.setFloat(3,productId);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while adding new product " + throwable, throwable);
        }


    }

    @Override
    public void increaseProductQuantity(int productId, Cart cart) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE cart_item SET quantity = quantity + 1 WHERE cart_id = ? AND product_id = ?";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,cart.getId() );
            st.setInt(2, productId);
            st.executeUpdate();

            System.out.println("done");
        } catch (SQLException throwable) {
            throw new RuntimeException("Error while updating a product in the cart. " + throwable, throwable);
        }




    }

    @Override
    public void decreaseProductQuantity(int productId,Cart cart) {

    }
}
