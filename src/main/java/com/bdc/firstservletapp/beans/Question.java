package com.bdc.firstservletapp.beans;

public class Question {
    private String textContent;
    private String answer;
    private String[] options;

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public Question(String textContent, String answer, String[] options) {
        this.textContent = textContent;
        this.answer = answer;
        this.options = options;
    }
}
