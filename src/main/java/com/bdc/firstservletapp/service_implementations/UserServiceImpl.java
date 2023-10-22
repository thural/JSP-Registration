package com.bdc.firstservletapp.service_implementations;

import com.bdc.firstservletapp.constants.SqlCommands;
import com.bdc.firstservletapp.models.User;
import com.bdc.firstservletapp.services.UserService;
import com.bdc.firstservletapp.connection.DatabaseConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends DatabaseConnection implements UserService {

    @Override
    public List<User> getAll() {
        try (Connection connection = connection()) {
            // prepare a database statement using a pre-prepared command
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SqlCommands.UserCommands.SELECT_ALL);
            // execute the prepared statement on database
            preparedStatement.execute();
            // create an array list to be populated with objects created using
            // returned values by the resultSet
            List<User> userList = new ArrayList<>();

            // get resultSet from the database statement
            ResultSet resultSet = preparedStatement.getResultSet();

            // create objects and set their fields using data from current resultSet
            // and add objects to the list
            while (resultSet.next()) {
                // create an object
                User user = new User();
                // populate the object fields using data from current resultSet
                user.setId(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setEmail(resultSet.getString("email"));
                user.setPhoneNumber(resultSet.getString("phone_number"));
                user.setPassword(resultSet.getString("password"));
                user.setCreateDate(resultSet.getObject("create_date", LocalDateTime.class));
                user.setUpdateDate(resultSet.getObject("update_date", LocalDateTime.class));
                // add current object to the list
                userList.add(user);
            }
            // return the populated list
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean add(User user) {
        try (Connection connection = connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.UserCommands.INSERT_INTO);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setObject(6, LocalDateTime.now());
            preparedStatement.setObject(7, LocalDateTime.now());
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean update(User user) {
        try (Connection connection = connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.UserCommands.UPDATE_SET);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhoneNumber());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setObject(6, LocalDateTime.now());
            preparedStatement.setLong(5, user.getId());
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean deleteById(long id) {
        try (Connection connection = connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.UserCommands.DELETE_BY_ID);
            preparedStatement.setLong(1, id);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean authenticate(String email, String password){
        try (Connection connection = connection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SqlCommands.UserCommands.CHECK_PASSWORD);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User getOne(String email, String password){
        try (Connection connection = connection()) {
            // prepare a database statement using a pre-prepared command
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SqlCommands.UserCommands.GET_ONE_RECORD);
            // execute the prepared statement on database
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            boolean isExecutionSuccess = preparedStatement.execute();
            // get resultSet from the database statement
            ResultSet resultSet = preparedStatement.getResultSet();
            // get the first result by calling next(),
            // otherwise you'll get "before start" exception even if DB query returns a single match
            resultSet.next();

            // nice to have some console output for easier debugging during development
            // should be removed at deployment otherwise its considered a major security risk
            if (isExecutionSuccess)
                System.out.println("DB query execution on getOne() of UserService was successful");
            else System.out.println("DB query execution on getOne() of UserService was failed");

            User user = new User();
            // populate the user object fields using data from current DB resultSet
            user.setId(resultSet.getLong("id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setPhoneNumber(resultSet.getString("phone_number"));
            user.setPassword(resultSet.getString("password"));
            user.setCreateDate(resultSet.getObject("create_date", LocalDateTime.class));
            user.setUpdateDate(resultSet.getObject("update_date", LocalDateTime.class));

            // return the populated object
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}