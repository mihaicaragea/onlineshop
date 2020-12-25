package com.codecool.shop.controller;

import com.codecool.shop.dao.DBImplimentation.UserDaoDB;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogIn", urlPatterns = {"/login"})
public class LogIn extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDataStore = UserDaoDB.getInstance();
        User dbUser = userDataStore.getByEmail(email);

        System.out.println(password);
        System.out.println(dbUser.getPassword());

        if(!password.equals(dbUser.getPassword())){
            System.out.println("wrong password");
        }else {
            response.sendRedirect("/index");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
