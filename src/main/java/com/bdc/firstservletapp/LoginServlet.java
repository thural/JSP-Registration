package com.bdc.firstservletapp;

import com.bdc.firstservletapp.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAuthSuccess = false;

        if(isAuthSuccess){
            getServletContext().getRequestDispatcher("/profile").forward(request, response);
        } else {
            request.setAttribute("error", "invalid credentials please try again");
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    public void destroy() {
        System.out.println("signup servlet has been destroyed");
    }
}