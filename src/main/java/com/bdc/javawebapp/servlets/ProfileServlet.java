package com.bdc.javawebapp.servlets;

import com.bdc.javawebapp.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "profileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("a GET method was called in profileServlet");
        // get the user object forwarded within the request
        User currentUser = (User) request.getAttribute("user");

        // build a custom error message to be displayed on the page
        StringBuilder error = new StringBuilder();
        if(currentUser == null) {
            request.setAttribute("user", new User());
            error.append("you are not logged in");
            request.setAttribute("error", error.toString());
        }

        // forward stored data within request on dispatching profile page
        getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    @Override
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("POST method was called in ProfileServlet");

        // display a warning message on the page
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        out.println("you've not written functionality for POST method of this route");
    }

    public void destroy() {
        System.out.println("profile servlet has been destroyed");
    }
}