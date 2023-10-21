package com.bdc.firstservletapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "aboutServlet", value = "/about")
public class AboutServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "About";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        System.out.println("get method has been called on about page");

        // Hello
        PrintWriter out = response.getWriter();
        out.println(message);
        out.println("this app is written using servlets and jsp");
        out.println("running on Apache Tomcat");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("HelloServlet POST method is called");
    }

    public void destroy() {
        System.out.println("about servlet has been destroyed");
    }
}