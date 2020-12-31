package com.codecool.shop.dao.DBImplimentation;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.model.*;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;

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
    public void create(User user){
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO cart(user_id) VALUES (?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, user.getId());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while adding new product " + throwable, throwable);
        }


    }



    @Override
    public Cart getCartByUserId(int userId) {
        ArrayList<CartItem> listOfItems = new ArrayList<>();
        Cart cart = new Cart(userId);

        try(Connection conn = dataSource.getConnection()) {
            String sql = "SELECT name,default_price,default_currency,quantity, cart_item.product_id FROM products\n" +
                    "JOIN cart  ON user_id = ?\n" +
                    "JOIN cart_item on cart.id = cart_item.cart_id\n" +
                    "where products.id = cart_item.product_id";

            String sql2 = "SELECT id from cart WHERE user_id =?";


            PreparedStatement st = conn.prepareStatement(sql);
            PreparedStatement st2 = conn.prepareStatement(sql2);

            st2.setInt(1,userId);
            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();
            ResultSet rs1 = st2.executeQuery();
            rs1.next();
            cart.setId(rs1.getInt(1));
            while(rs.next()){
                CartItem cart_item = new CartItem(rs.getInt(5), rs.getString(1),rs.getFloat(2),rs.getString(3),rs.getInt(4));
                listOfItems.add(cart_item);
            }
            listOfItems.sort(Comparator.comparing(CartItem::getProductId));
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

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while updating a product in the cart. " + throwable, throwable);
        }




    }

    @Override
    public void decreaseProductQuantity(int productId,Cart cart) {

        try(Connection conn = dataSource.getConnection()) {
            String sql = "UPDATE cart_item SET quantity = quantity - 1 WHERE cart_id = ? AND product_id = ?";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,cart.getId() );
            st.setInt(2, productId);
            st.executeUpdate();

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while updating a product in the cart. " + throwable, throwable);
        }

    }

    @Override
    public void deleteProductFromCart(int productId, Cart cart) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "DELETE FROM cart_item  WHERE cart_id = ? AND product_id = ?";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1,cart.getId() );
            st.setInt(2, productId);
            st.executeUpdate();

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while updating a product in the cart. " + throwable, throwable);
        }

    }
}
