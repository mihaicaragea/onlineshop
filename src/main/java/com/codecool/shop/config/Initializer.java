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
        Supplier lenovo = new Supplier("Lenovo", "Computers");
        Supplier nokia = new Supplier("Nokia", "Smartphones");
        Supplier fitbit = new Supplier("Fitbit", "Smartwatches");
        Supplier lg = new Supplier("LG", "TV");

//        supplierDataStore.add(lg);

        //setting up a new product category
//        ProductCategory tablet = new ProductCategory("Tablet", "Hardware", "A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.");
//        ProductCategory smartPhones = new ProductCategory("Smartphones", "Hardware", "A smartphone is a mobile device that combines cellular and mobile computing functions into one unit.");
//        ProductCategory smartWatches = new ProductCategory("Smartwathces", "Hardware", "A smartwatch is a wearable computer in the form of a watch; modern smartwatches provide a local touchscreen interface for daily use");
        ProductCategory tv = new ProductCategory("TV","Hardware","Telegram television (also known as a TV) is a machine with a screen. Televisions receive broadcasting signals and change them into pictures and sound. ");
//        productCategoryDataStore.add(tv);

        //setting up products and printing it
//        productDataStore.add(new Product("Amazon Fire", 49.9f, "USD", "Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.", tablet, amazon));
//        productDataStore.add(new Product("Lenovo IdeaPad Miix 700", 479, "USD", "Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.", tablet, lenovo));
//        productDataStore.add(new Product("Amazon Fire HD 8", 89, "USD", "Amazon's latest Fire HD 8 tablet is a great value for media consumption.", tablet, amazon));
//        productDataStore.add(new Product("Nokia 9", 400, "USD", "With high-quality hardware, Android's intuitive software, and only the most essential apps loaded, Android One phones delivers a seamless phone experience.", smartPhones, nokia));
//        productDataStore.add(new Product("Fitbit Versa 3", 198, "USD", "Run, bike, hike and more phone-free—and see your real-time pace & distance—with built-in GPS. Then check out your workout intensity map in the Fitbit app", smartWatches, fitbit));
//        productDataStore.add(new Product("LG 43Inch 4K", 266, "USD", "REAL 4K DISPLAY: Enjoy 4K resolution for your 4K movies and shows. Its clarity in every moment, with pristine color, light and detail.", tv, lg));
    }
}
