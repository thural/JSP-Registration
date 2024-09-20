package com.bdc.firstservletapp.servlets;

import com.bdc.firstservletapp.models.User;
import com.bdc.firstservletapp.services.UserService;
import com.bdc.firstservletapp.services.service_impls.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "signupServlet", value = "/signup")
public class SignupServlet extends HttpServlet {

    public void init() {
    }

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
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);

        // insert fields of user object as a new record into the table
        // and store result of the execute() operation
        boolean registerSuccess = userService.addOne(user);

        // get session
        HttpSession session = request.getSession(false);

        if (registerSuccess) {
            System.out.println("registration was successful");
            // store the new user object in the request before forwarding,
            // so it can be accessed by profile servlet and page
            request.setAttribute("user", user);
            // store user in session for user login filtering
            session.setAttribute("user", user);
            // get User list from services
            List<User> userList = userService.getAll();
            // pass the user list to the request
            request.setAttribute("userList", userList);
            // forward request and response to profile page
            getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
        } else {
            // forward it back to the signup page with the error message
            request.setAttribute("error", "a user with this email already exists");
            // pass field values to the request to persist form data during forwarding
            request.setAttribute("first_name", firstName);
            request.setAttribute("last_name", lastName);
            request.setAttribute("phone_number", phoneNumber);
            getServletContext().getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }

    public void destroy() {
        System.out.println("signup servlet has been destroyed");
    }
}