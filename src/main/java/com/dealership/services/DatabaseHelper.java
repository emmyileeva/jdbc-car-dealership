package com.dealership.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {
    private static final String URL = "jdbc:sqlserver://localhost:1433;" +
            "database=CarDealership;" +
            "user=sa;" +
            "password=SalemPuppy123!;" +
            "encrypt=true;" +
            "trustServerCertificate=true;";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}