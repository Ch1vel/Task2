package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;import java.sql.Statement;

public class Util {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String URL = "jdbc:mysql://localhost:3306/testdb";


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER_NAME,PASSWORD);
    }







}
