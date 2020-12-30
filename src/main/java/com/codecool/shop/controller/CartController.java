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
        String plusButton = req.getParameter("PlusButton");
        String minusButton = req.getParameter("MinusButton");
        String removeButton = req.getParameter("RemoveButton");

        int userID = (int) req.getSession().getAttribute("userId");

        Cart cart = cartDataStore.getCartByUserId(userID);
        ArrayList<CartItem> products = cart.getProducts();


        if(addToCart!=null){
            int productId = Integer.parseInt(req.getParameter("productId"));
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


        if(plusButton!=null){
            int productId = Integer.parseInt(req.getParameter("productId"));
            cartDataStore.increaseProductQuantity(productId, cart);
            resp.sendRedirect("/cart");
        }

        if (minusButton!=null){
            int productId = Integer.parseInt(req.getParameter("productId"));
            cartDataStore.decreaseProductQuantity(productId, cart);
            cart = cartDataStore.getCartByUserId(userID);
            products = cart.getProducts();
            for(CartItem product:products){
                if(product.getQuantity()<=0){
                    cartDataStore.deleteProductFromCart(productId, cart);
                }
            }
            resp.sendRedirect("/cart");
        }

        if(removeButton!=null){
            int productId = Integer.parseInt(req.getParameter("productId"));
            cartDataStore.deleteProductFromCart(productId,cart);
            resp.sendRedirect("/cart");
        }


        
        context.setVariable("cart", cart.getProducts());
        engine.process("cart/cart.html", context, resp.getWriter());

    }
}
