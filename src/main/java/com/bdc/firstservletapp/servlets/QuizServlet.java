package com.bdc.firstservletapp.servlets;

import com.bdc.firstservletapp.beans.ErrorBean;
import com.bdc.firstservletapp.beans.Question;
import com.bdc.firstservletapp.models.User;
import com.bdc.firstservletapp.service_impls.UserServiceImpl;
import com.bdc.firstservletapp.services.UserService;
import com.bdc.firstservletapp.utils.ToJson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "quizServlet", value = "/quiz")
public class QuizServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/quiz.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public void destroy() {
        System.out.println("login servlet has been destroyed");
    }
}