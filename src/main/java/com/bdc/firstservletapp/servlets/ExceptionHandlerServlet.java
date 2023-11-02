package com.bdc.firstservletapp.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ExceptionHandlerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Analyze the servlet exception
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) statusCode = 500;

        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) servletName = "Unknown";

        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) requestUri = "Unknown";

        System.out.println("ERROR STATUS CODE: " + statusCode);

        // Set response content type
        response.setContentType("text/html");

        // write and output the html content
        PrintWriter out = response.getWriter();
        out.write("<html><head><title>Exception/Error Details</title></head><body>");
        if (statusCode == 500) {
            out.write("<h3>Exception Details</h3>");
            out.write("<ul><li>Servlet Name:" + servletName + "</li>");
            out.write("<li>Exception Name:" + throwable.getClass().getName() + "</li>");
            out.write("<li>Requested URI:" + requestUri + "</li>");
            out.write("<li>Exception Message:" + throwable.getMessage() + "</li>");
            out.write("</ul>");
        } else {
            out.write("<h3>Error Details</h3>");
            out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
            out.write("<strong>Requested URI</strong>:" + requestUri);
        }
        out.write("<br><br>");
        out.write("<a href=\"index.html\">Home Page</a>");
        out.write("</body></html>");
    }

}