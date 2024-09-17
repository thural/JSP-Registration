package com.bdc.firstservletapp.servlets;

import com.bdc.firstservletapp.beans.Question;
import com.bdc.firstservletapp.utils.CustomJsonSerializer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.bdc.firstservletapp.utils.CustomJsonSerializer.formatQuizResultJSON;

@WebServlet(name = "quizAPIServlet", value = "/quizAPI/*")
public class QuizAPIServlet extends HttpServlet {

    private static List<Question> getQuestions() {
        List<Question> questionList = new ArrayList<Question>();
        String[] options1 = {"Earth", "Mars", "Venera", "Mercury"};
        Question question1 = new Question("what is the 3-rd planet from Sun?", "Earth", options1);
        String[] options2 = {"Moscow", "Ankara", "Baku", "Berlin"};
        Question question2 = new Question("which is the capital of Germany?", "Berlin", options2);
        questionList.add(question1);
        questionList.add(question2);
        return questionList;
    }

    public void init() {
        System.out.println("QuizAPI servlet has been initiated");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // create and add question objects to the list
        List<Question> questionList = getQuestions();

        // stringify the array list to JSON format using the custom parser
        String questionsJSON = CustomJsonSerializer.serializeList(questionList);

        // set the response content type and encoding
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // write the JSON string to the response
        response.getWriter().write(questionsJSON);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get the path info (the last part of the request path, in this case "/answers")
        String pathInfo = request.getPathInfo();
        System.out.println("PATH INFO: " + pathInfo);

        // call a method to handle the request based on path info
        if (pathInfo.equals("/answers")) {
            returnResult(request, response);
            // you may add your cases here for custom API requests
        } else {
            System.out.println("unhandled request to /QuizAPI");
        }

    }

    private void returnResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // create a string builder to store string from the POST request body
        StringBuilder sb = new StringBuilder();
        // get the reader from request
        BufferedReader reader = request.getReader();
        // read and store each line from request body
        String line;
        while ((line = reader.readLine()) != null) sb.append(line);
        // finally, convert string builder data to string
        String data = sb.toString();

        // build a result JSON string using the custom method in utils package
        String outputJSON = formatQuizResultJSON(data);

        System.out.println("OUTPUT JSON STRING: " + outputJSON);

        // Write the JSON string to the response using the write method
        response.getWriter().write(outputJSON);
    }

    public void destroy() {
        System.out.println("QuizAPI servlet has been destroyed");
    }
}