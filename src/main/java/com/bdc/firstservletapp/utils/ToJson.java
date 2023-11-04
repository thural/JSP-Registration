package com.bdc.firstservletapp.utils;

import com.bdc.firstservletapp.beans.Question;

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
}