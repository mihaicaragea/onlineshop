package com.codecool.shop.dao.DBImplimentation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class SupplierDaoDB  implements SupplierDao {
    private DataSource dataSource;
    private SupplierDaoDB instance;

    public SupplierDaoDB(DataSource dataSource){
        this.dataSource = dataSource;
        instance = this;
    }

    public SupplierDaoDB getInstance() {
        return instance;
    }


    @Override
    public void add(Supplier supplier) {
        String sql = "INSERT INTO product_supplier(name, description) VALUES (?, ?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());

            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            supplier.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }
}
