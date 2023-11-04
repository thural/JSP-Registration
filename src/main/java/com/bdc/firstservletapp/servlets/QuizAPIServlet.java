package com.bdc.firstservletapp.servlets;

import com.bdc.firstservletapp.beans.Question;
import com.bdc.firstservletapp.utils.ToJson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "quizAPIServlet", value = "/quizAPI/*")
public class QuizAPIServlet extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // create and add question objects to the list
        List<Question> questionList = getQuestions();

        // stringify the array list to JSON format using the custom parser
        String questionsJSON = ToJson.arrayFromQuestionList(questionList);

        // set the response content type and encoding
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // write the JSON string to the response
        response.getWriter().write(questionsJSON);
    }

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get the path info (the last part of the request path, in this case "/answers")
        String pathInfo = request.getPathInfo();
        System.out.println("PATH INFO: " + pathInfo);

        // call a method to handle the request based on path info
        switch (pathInfo) {
            case "/answers":
                returnResult(request, response);
                break;
            // you may add your cases here for custom API requests
            default:
                System.out.println("unhandled request to /QuizAPI");
        }

    }

    private void returnResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // create a string builder to store string from request body
        StringBuilder sb = new StringBuilder();
        // get the reader from request
        BufferedReader reader = request.getReader();
        // read and store each line from request body
        String line;
        while ((line = reader.readLine()) != null) sb.append(line);
        // finally, convert string builder data to string
        String data = sb.toString();

        // print raw json string data
        System.out.println("SB DATA STRING: " + data);

        // create a list of objects from json string data
        JSONArray jsonArray = new JSONArray(data);

        // you can access each json object and it's values from the parsed json array
        // you can also loop over it and re-construct (real) java objects
        // into an array list by getting the values of parsed json objects
        String output = ((JSONArray) jsonArray).getJSONObject(0).getString("name").toString();
        System.out.println("OUTPUT FROM FIRST JSON OBJECT: " + output);

        // Write the JSON string to the response using the write method
        response.getWriter().write("{\"success\": true}");
    }

    public void destroy() {
        System.out.println("login servlet has been destroyed");
    }
}