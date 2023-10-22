package com.bdc.firstservletapp.constants;

public final class SqlCommands {
    // SQL commands for prepared statements, grouped by database entity/table


    // the commands describe CRUD operations:
    public static class UserCommands {

        // Create
        public final static String INSERT_INTO = "INSERT INTO user(first_name, last_name, email, phone_number, password, create_date, update_date) values(?,?,?,?,?,?,?)";
        // Read
        public final static String SELECT_ALL = "SELECT * FROM user";
        // Update
        public final static String UPDATE_SET = "UPDATE course SET first_name=?, last_name=?, email=?, phone_number=?, password=?, update_date=? where id=?";
        // Delete
        public final static String DELETE_BY_ID = "DELETE FROM user WHERE id=?";

        public final static String CHECK_PASSWORD = "SELECT * FROM user WHERE email=? AND password = ?";

        public final static String GET_ONE_RECORD = "SELECT * FROM user WHERE email=? AND password =?";
    }
}
