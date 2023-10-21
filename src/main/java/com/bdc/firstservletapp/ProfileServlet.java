package com.bdc.firstservletapp;

import com.bdc.firstservletapp.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "profileServlet", value = "/profile")
public class ProfileServlet extends HttpServlet {

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User) request.getAttribute("user");

        StringBuilder error = new StringBuilder();

        if(currentUser == null) {
            request.setAttribute("user", new User());
            error.append("you are not logged in");
            request.setAttribute("error", error.toString());
        }

        getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);

    }

    public void destroy() {
        System.out.println("signup servlet has been destroyed");
    }
}