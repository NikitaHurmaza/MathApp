package com.example.mathapp;

import rootFinder.EquationChecker;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;

public class EquationDatabaseManager {
    private Connection connection;
    public EquationDatabaseManager() throws SQLException {
        Properties props = new Properties();
        try (BufferedReader reader = Files.newBufferedReader(Path.of("config.properties"))) {
            props.load(reader);
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveEquation(String equation) throws SQLException {
        if (EquationChecker.checkEquation(equation) && EquationChecker.checkParentheses(equation)) {
            String sql = "INSERT INTO equations (equation) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, equation);
                statement.executeUpdate();
            }
        }
    }
}

