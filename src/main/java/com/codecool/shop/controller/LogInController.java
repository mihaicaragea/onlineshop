package com.codecool.shop.controller;

import com.codecool.shop.dao.DBImplimentation.UserDaoDB;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogIn", urlPatterns = {"/login"})
public class LogInController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDao userDataStore = UserDaoDB.getInstance();
        User dbUser = userDataStore.getByEmail(email);


        if(!password.equals(dbUser.getPassword())){
            System.out.println("wrong password");
        }else {
            request.getSession().setAttribute("user", dbUser.getFirstName());
            request.getSession().setAttribute("userId", dbUser.getId());

            response.sendRedirect("/index");
        }

    }

}
