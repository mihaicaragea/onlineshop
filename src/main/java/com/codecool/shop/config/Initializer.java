package com.codecool.shop.config;

import com.codecool.shop.dao.DBImplimentation.ProductCategoryDaoDB;
import com.codecool.shop.dao.DBImplimentation.ProductDaoDB;
import com.codecool.shop.dao.DBImplimentation.SupplierDaoDB;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.MemoryImplementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.MemoryImplementation.ProductDaoMem;
import com.codecool.shop.dao.MemoryImplementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@WebListener
public class Initializer implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DataSource dataSource = null;

        try {
            dataSource = DatabaseManager.connect();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductDao productDataStore =  new ProductDaoDB(dataSource);

//        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = new ProductCategoryDaoDB(dataSource);

//        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        SupplierDaoDB supplierDataStore = new SupplierDaoDB(dataSource);

        //setting up a new supplier
        Supplier amazon = new Supplier("Amazon", "Digital content and services");
//        supplierDataStore.add(amazon);
        Supplier lenovo = new Supplier("Lenovo", "Computers");
//        supplierDataStore.add(lenovo);

        //setting up a new product category
        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
//        productCategoryDataStore.add(tablet);

        //setting up products and printing it
//        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
//        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
//        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
    }
}
