package com.bdc.firstservletapp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "CustomExceptionServlet", value = "/exception")
public class CustomExceptionServlet extends HttpServlet {

    public void init() {
        System.out.println("CustomExceptionServlet was initialized");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new ArithmeticException();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CustomExceptionServlet POST method was called");
    }

    public void destroy() {
        System.out.println("about servlet has been destroyed");
    }
}