package com.bdc.firstservletapp.beans;

import java.io.Serializable;

public class ErrorBean implements Serializable {

    public String title;
    public String message;

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Error{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
