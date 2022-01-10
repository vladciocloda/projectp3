package com.example.projectp3;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    public Connection databaseLink;

    public Connection getConnection() {
        String databaseName = "projectp3";
        String databaseUser = "root";
        String databasePassword = "";
        String url = "jdbc:mysql://localhost/" + databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return databaseLink;
    }
}
