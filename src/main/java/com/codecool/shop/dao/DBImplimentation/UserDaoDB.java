package com.codecool.shop.dao.DBImplimentation;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.User;

import javax.sql.DataSource;
import java.sql.*;

public class UserDaoDB  implements UserDao {
    private final DataSource dataSource;

    private static UserDaoDB instance;

    public UserDaoDB(DataSource dataSource){
        this.dataSource = dataSource;
        instance = this;
    }

    public static UserDaoDB getInstance() {
        return instance;
    }

    @Override
    public void add(User user) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "INSERT INTO registered_users(first_name, last_name, country, address, postcode, town, phone, email, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, user.getFirstName());
            st.setString(2, user.getLastName());
            st.setString(3, user.getCountry());
            st.setString(4, user.getAddress());
            st.setString(5, user.getPostCode());
            st.setString(6, user.getTown());
            st.setString(7, user.getPhoneNumber());
            st.setString(8, user.getEmail());
            st.setString(9, user.getPassword());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            rs.next();
            user.setId(rs.getInt(1));

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while adding new User. " + throwable, throwable);
        }

    }

    @Override
    public User getByEmail(String email) {
        try (Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM registered_users WHERE email = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if (!rs.next()) {
                return null;
            }
            User user = new User(rs.getString("first_name"), rs.getString("last_name"), rs.getString("country"), rs.getString("address"), rs.getString("postcode"), rs.getString("town"), rs.getString("phone"), rs.getString("email"), rs.getString("password"));
            user.setId(rs.getInt(1));
            return user;

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while trying to find a user. " + throwable, throwable);
        }
    }
}
