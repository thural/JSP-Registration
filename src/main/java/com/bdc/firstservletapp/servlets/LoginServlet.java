package com.bdc.firstservletapp.servlets;

import com.bdc.firstservletapp.beans.ErrorBean;
import com.bdc.firstservletapp.models.User;
import com.bdc.firstservletapp.services.service_impls.UserServiceImpl;
import com.bdc.firstservletapp.services.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost method was called in login servlet");
        // get parameters from POST request made on submission of login form on the login page
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        // instantiate a user service to be used for DB queries
        UserService userService = new UserServiceImpl();
        // use email and password for the DB query of the authenticate method
        // and store result of the execute() operation
        boolean isAuthSuccess = userService.authenticate(email, password);
        // get session
        HttpSession session = request.getSession(false);

        if (isAuthSuccess) {
            System.out.println("auth was successful");
            // pull record of the user fromDB and create a user object using that data
            User currentUser = userService.getOne(email, password);
            // pass the user object to the request before forwarding to the profile page
            request.setAttribute("user", currentUser);
            // register user to session
            session.setAttribute("user", currentUser);
            // get User list from services
            List<User> userList = userService.getAll();
            // pass the user list to the request
            request.setAttribute("userList", userList);
            // forward request directly to profile page, instead of /profile route,
            // otherwise a post request or doPost() will be triggered on profileServlet
            getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
        } else {
            System.out.println("auth was failed");
            // forward back to the login page with the error message
            ErrorBean errObj = new ErrorBean();
            errObj.setTitle("login error");
            errObj.setMessage("invalid credentials please try again");
            System.out.println("error object: " + errObj);
            request.setAttribute("error", errObj);
            getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    public void destroy() {
        System.out.println("login servlet has been destroyed");
    }
}