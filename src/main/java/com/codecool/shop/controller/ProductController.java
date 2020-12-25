package com.codecool.shop.controller;

import com.codecool.shop.dao.DBImplimentation.ProductCategoryDaoDB;
import com.codecool.shop.dao.DBImplimentation.ProductDaoDB;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.MemoryImplementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.MemoryImplementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoDB.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoDB.getInstance();
        int category_id =  1;
        if (req.getParameter("category")!=null){
            category_id = Integer.parseInt(req.getParameter("category"));
        }


        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("category", productCategoryDataStore.find(category_id));
        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(category_id)));
        engine.process("product/index.html", context, resp.getWriter());
    }

}
