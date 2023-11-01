package com.bdc.javawebapp.beans;

public class ErrorBean {
    public String title;
    public String message;

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public ErrorBean(String title, String message) {
        this.title = title;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
