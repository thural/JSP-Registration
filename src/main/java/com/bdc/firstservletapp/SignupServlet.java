package com.bdc.firstservletapp;

import com.bdc.firstservletapp.models.User;
import com.bdc.firstservletapp.service_implementations.UserServiceImpl;
import com.bdc.firstservletapp.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "signupServlet", value = "/signup")
public class SignupServlet extends HttpServlet {

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get parameters from POST request made by the signup form
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phone_number");
        String password = request.getParameter("password");

        // instantiate a user service for DB queries
        UserService userService = new UserServiceImpl();
        // create a user object using parameter values of the form
        User user = new User(firstName, lastName, email, phoneNumber, password);
        // insert fields of user object as a new record into the table
        // and store result of the execute() operation
        boolean registerSuccess = userService.add(user);

        if(registerSuccess){
            System.out.println("registration was successful");
            // store the new user object in the request before forwarding,
            // so it can be accessed by profile servlet and page
            request.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/profile.jsp").forward(request,response);
        } else {
            // forward it back to the signup page with the error message
            request.setAttribute("error", "a user with this email already exists");
            getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }

    public void destroy() {
        System.out.println("signup servlet has been destroyed");
    }
}