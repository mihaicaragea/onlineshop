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
}
