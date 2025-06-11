package com.dealership.services.ui;

import com.dealership.models.Contract;

import java.util.List;
import java.util.Scanner;

public class AdminUserInterface {
    private Scanner scanner = new Scanner(System.in);

    public void displayAdminMenu() {
        String choice = "";

        while (!choice.equals("exit")) {
            System.out.println("Admin Menu:");
            System.out.println("1. View all contracts");
            System.out.println("2. View the last 10 contracts");
            System.out.println("3. Type 'exit' to exit the admin menu");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    viewAllContracts();
                    break;
                case "2":
                    viewLast10Contracts();
                    break;
                case "3":
                    System.out.println("Exiting admin menu and returning to main menu...");
                    choice = "exit";
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void viewAllContracts() {
        // Replace with database call to fetch all contracts
    }

    public void viewLast10Contracts() {
        // Replace with database call to fetch the last 10 contracts
    }
}
