package com.dealership.services.ui;

import com.dealership.dao.LeaseDao;
import com.dealership.dao.SalesDao;
import com.dealership.models.Contract;
import com.dealership.services.DatabaseHelper;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class AdminUserInterface {
    private Scanner scanner = new Scanner(System.in);

    public void displayAdminMenu() {
        String choice = "";

        while (!choice.equals("exit")) {
            System.out.println("Admin Menu:");
            System.out.println("1. View all contracts");
            System.out.println("2. Type 'exit' to exit the admin menu");
            System.out.print("Please enter your choice: ");

            choice = scanner.nextLine();

            switch (choice) {
                case "1" -> viewAllContracts();
                case "2" -> System.out.println("Exiting admin menu and returning to main menu...");
                default -> {
                    if (!choice.equals("exit")) {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            }
        }
    }

    public void viewAllContracts() {
        try {
            Connection connection = DatabaseHelper.getConnection();
            SalesDao salesDao = new SalesDao(connection);
            LeaseDao leaseDao = new LeaseDao(connection);

            List<? extends Contract> salesContracts = salesDao.getAllSalesContracts();
            List<? extends Contract> leaseContracts = leaseDao.getAllLeaseContracts();

            System.out.println("📄 All Sales Contracts:");
            salesContracts.forEach(c -> System.out.println(c.toString()));

            System.out.println("\n📄 All Lease Contracts:");
            leaseContracts.forEach(c -> System.out.println(c.toString()));

        } catch (Exception e) {
            System.out.println("Error loading contracts: " + e.getMessage());
        }
    }
}
