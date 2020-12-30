package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DBImplimentation.CartDaoDB;
import com.codecool.shop.model.Cart;
import com.codecool.shop.model.CartItem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        CartDao cartDataStore = CartDaoDB.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String addToCart = req.getParameter("addBtn");

        int userID = (int) req.getSession().getAttribute("userId");


        Cart cart = cartDataStore.getCartByUserId(userID);



        if(addToCart!=null){
            int productId = Integer.parseInt(req.getParameter("productId"));
            ArrayList<CartItem> products = cart.getProducts();
            for(CartItem product : products){
                if(product.getProductId()==productId){
                    cartDataStore.increaseProductQuantity(productId,cart);
                    resp.sendRedirect("/index");
                    return;
                }
            }
            cartDataStore.addProductToCart(productId, cart);
            resp.sendRedirect("/index");
        }

        context.setVariable("cart", cart.getProducts());
        engine.process("cart/cart.html", context, resp.getWriter());

    }
}
