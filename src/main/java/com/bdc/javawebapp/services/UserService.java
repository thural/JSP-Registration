package com.bdc.javawebapp.services;

import com.bdc.javawebapp.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    boolean add(User user);

    boolean update(User user);

    boolean deleteById(long id);

    boolean authenticate(String email, String password);

    User getOne(String email, String password);
}
