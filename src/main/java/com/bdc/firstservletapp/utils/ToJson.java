package com.bdc.firstservletapp.utils;

import com.bdc.firstservletapp.beans.Question;
import org.json.JSONArray;

import java.util.List;

public class ToJson {
    public static String parseQuestionObj(Question question) {
        String[] options = question.getOptions();

        StringBuilder sb = new StringBuilder();

        // the JSON object opening
        sb.append("{");
        // stringify text content
        sb.append("\"textContent\"");
        sb.append(":");
        sb.append("\"" + question.getTextContent() + "\"");
        sb.append(",");
        // stringify answer
        sb.append("\"answer\"");
        sb.append(":");
        sb.append("\"" + question.getAnswer() + "\"");
        sb.append(",");
        // stringify options
        sb.append("\"options\"");
        sb.append(":");
        sb.append("[");
        for (int i = 0; i < options.length; i++) {
            sb.append("\"" + options[i] + "\"");
            if (i != options.length - 1) sb.append(",");
        }
        sb.append("]");

        // close the JSON object
        sb.append("}");

        return sb.toString();
    }

    public static String arrayFromQuestionList(List<Question> list) {
        StringBuilder sb = new StringBuilder();
        // add opening bracket for json array
        sb.append("[");
        // add each parsed question object to the json array
        for (int i = 0; i < list.size(); i++) {
            Question question = list.get(i);
            sb.append(parseQuestionObj(question));
            if (i != list.size() - 1) sb.append(",");
        }
        // add closing bracket for json array
        sb.append("]");
        return sb.toString();
    }

    public static String buildResultJSON(String data){
        StringBuilder sb = new StringBuilder();
        // open the json array
        sb.append("[");

        // you can access each json object and it's values from the parsed json array
        // into an array list by getting the values of parsed json objects

        // create a list of JSON objects from string data in JSON format
        JSONArray jsonArray = new JSONArray(data);
        // loop over each json object and construct a result json string
        for (int i = 0; i < jsonArray.length(); i++){
            String questionName = ((JSONArray) jsonArray).getJSONObject(i).getString("name");
            String userAnswer = ((JSONArray) jsonArray).getJSONObject(i).getString("value");
            String correctAnswer = ((JSONArray) jsonArray).getJSONObject(i).getString("correctAnswer");

            // opening of current json object string
            sb.append("{");

            // construct key value pairs
            sb.append("\"questionName\"");
            sb.append(":");
            sb.append("\""+ questionName +"\"");

            sb.append(",");

            sb.append("\"answer\"");
            sb.append(":");
            sb.append("\""+ correctAnswer +"\"");

            sb.append(",");

            sb.append("\"userAnswer\"");
            sb.append(":");
            sb.append("\""+ userAnswer +"\"");

            // closing of current json object string
            sb.append("}");

            if (i!= jsonArray.length() - 1) sb.append(",");
        }
        // close the json array
        sb.append("]");

        return sb.toString();
    }
}