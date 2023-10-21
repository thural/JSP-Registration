package com.bdc.firstservletapp;

import com.bdc.firstservletapp.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "signupServlet", value = "/signup")
public class SignupServlet extends HttpServlet {

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean emailAlreadyExists = false;
        String name = request.getParameter("first_name");
        String psw = request.getParameter("psw");
        Integer age = 28;


        if(!emailAlreadyExists){
            request.setAttribute("user", new User(name, age, psw));
            getServletContext().getRequestDispatcher("/profile").forward(request,response);
        } else {
            request.setAttribute("error", "a user with this email already exists");
            getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }

    public void destroy() {
        System.out.println("signup servlet has been destroyed");
    }
}