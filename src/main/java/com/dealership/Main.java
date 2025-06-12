package com.dealership;

import com.dealership.services.DatabaseHelper;
import com.dealership.services.ui.UserInterface;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseHelper.getConnection()) {
            System.out.println("Connection to CarDealership DB successful!");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
        UserInterface ui = new UserInterface();
        ui.display();
    }
}
