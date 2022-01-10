package com.example.projectp3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionUtil {

    public static Connection getConnection() throws Exception{
        String dbRoot = "jdbc:mysql://";
        String hostName = "localhost:3306/";
        String dbName = "projectp3";
        String dbUrl = dbRoot+hostName+dbName;

        String hostUsername = "root";
        String hostPassword = "";

        Class.forName("com.mysql.jdbc.Driver");
        Connection myConn = (Connection)DriverManager.getConnection(dbUrl, hostUsername, hostPassword);

        return myConn;
    }
}
