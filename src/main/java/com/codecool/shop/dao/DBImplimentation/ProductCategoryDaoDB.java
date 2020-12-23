package com.codecool.shop.dao.DBImplimentation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.ProductCategory;


import javax.sql.DataSource;

import java.sql.*;
import java.util.List;


public class ProductCategoryDaoDB  implements ProductCategoryDao {
    private  final DataSource dataSource;
    private static  ProductCategoryDaoDB instance;

    public ProductCategoryDaoDB(DataSource dataSource){
        this.dataSource = dataSource;
        instance = this;
    }


    public static ProductCategoryDaoDB getInstance() {
        return instance;
    }



    @Override
    public void add(ProductCategory category) {
        String sql = "INSERT INTO product_category(name, description, department) VALUES (?, ?, ?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getDepartment());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();
            category.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ProductCategory find(int id) {
        try(Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM product_category WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(!rs.next()){
                return null;
            }
            ProductCategory productCategory = new ProductCategory(rs.getString("name"), rs.getString("description"), rs.getString("department"));
            productCategory.setId(rs.getInt(1));
            return productCategory;

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while trying to find a user. " + throwable, throwable);
        }

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<ProductCategory> getAll() {
        return null;
    }
}
