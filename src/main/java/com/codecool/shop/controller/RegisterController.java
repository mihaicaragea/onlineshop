package com.codecool.shop.controller;

import com.codecool.shop.dao.CartDao;
import com.codecool.shop.dao.DBImplimentation.CartDaoDB;
import com.codecool.shop.dao.DBImplimentation.UserDaoDB;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Register", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String country = request.getParameter("country");
        String address = request.getParameter("address");
        String postcode = request.getParameter("postcode");
        String town = request.getParameter("town");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User newUser = new User(firstName,lastName,country, address, postcode, town, phone, email, password);

        UserDao userDataStore = UserDaoDB.getInstance();
        CartDao cartDataStore = CartDaoDB.getInstance();

        userDataStore.add(newUser);
        cartDataStore.create(newUser);

        response.sendRedirect("/index");

    }
}
