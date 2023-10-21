package com.bdc.firstservletapp.models;

import java.io.Serializable;

public class User implements Serializable {
    String name;
    String psw;
    Integer age;

    public User(){
        this.name = "guest";
        this.psw = "guest";
        this.age = null;
    }

    public User(String name, Integer age, String psw) {
        this.name = name;
        this.psw = psw;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
