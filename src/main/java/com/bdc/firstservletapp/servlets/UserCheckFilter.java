package com.bdc.firstservletapp.servlets;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class UserCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);

        String loginURI = request.getContextPath() + "/login";
        String signupURI = request.getContextPath() + "/signup";
        String profileURI = request.getContextPath() + "/profile";

        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);
        boolean signupRequest = request.getRequestURI().equals(signupURI);

        if (loggedIn || loginRequest || signupRequest) {
            if(loggedIn && (signupRequest || loginRequest)) {
                response.sendRedirect(profileURI);
            } else chain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }
    }
}