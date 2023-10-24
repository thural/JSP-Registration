package com.bdc.firstservletapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ErrorExampleServlet", value = "/exception")
public class ErrorExampleServlet extends HttpServlet {

    public void init() {
        System.out.println("ErrorExampleServlet was initialized");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        throw new NullPointerException();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("ErrorExampleServlet POST method was called");
    }

    public void destroy() {
        System.out.println("about servlet has been destroyed");
    }
}