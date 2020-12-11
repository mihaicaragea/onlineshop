package com.codecool.shop.dao.DBImplimentation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoDB  implements ProductDao {
    private final DataSource dataSource;
    private static ProductDaoDB instance;

    public ProductDaoDB(DataSource dataSource){
        this.dataSource = dataSource;
        instance = this;
    }

    public static ProductDaoDB getInstance() {
        return instance;
    }


    @Override
    public void add(Product product) {
        String sql = "INSERT INTO products(name, description,default_price, default_currency,category_id,supplier_id) VALUES (?,?,?,?,?,?)";
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getDefaultPrice());
            statement.setString(4, product.getDefaultCurrency());
            statement.setInt(5, product.getProductCategory().getId());
            statement.setInt(6, product.getSupplier().getId());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            resultSet.next();

            product.setId(resultSet.getInt(1));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Product find(int id) { return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        List<Product> listOfProducts = new ArrayList<>();
        try(Connection conn = dataSource.getConnection()) {
            String sql = "SELECT * FROM products\n" +
                    "INNER JOIN product_supplier ON products.supplier_id = product_supplier.id\n" +
                    "WHERE products.category_id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, productCategory.getId());
            ResultSet rs = st.executeQuery();
            System.out.println("after query");
            while(rs.next()){
                Supplier supplier = new Supplier(rs.getString(9),rs.getString(10));
                supplier.setId(rs.getInt(8));
                System.out.println(supplier.getName());
                Product product = new Product(rs.getString(2),rs.getFloat(4), rs.getString(5), rs.getString(3), productCategory, supplier);
                product.setId(rs.getInt(1));
                listOfProducts.add(product);
            }
            System.out.println(listOfProducts);
            return listOfProducts;

        } catch (SQLException throwable) {
            throw new RuntimeException("Error while trying to find a user. " + throwable, throwable);
        }

    }
}
