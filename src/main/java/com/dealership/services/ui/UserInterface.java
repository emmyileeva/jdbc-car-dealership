package com.dealership.services.ui;

import com.dealership.models.*;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    private void init() {
    }

    public void display() {
        init();

        String choice = "";

        while (!choice.equals("exit")) {
            System.out.println("Welcome to the Dealership!");
            System.out.println("1. View all vehicles");
            System.out.println("2. Search by make and model");
            System.out.println("3. Search by year");
            System.out.println("4. Search by color");
            System.out.println("5. Search by mileage");
            System.out.println("6. Search by type");
            System.out.println("7. Search by price");
            System.out.println("8. Add a vehicle");
            System.out.println("9. Remove a vehicle");
            System.out.println("10. Sell or lease a vehicle");
            System.out.println("11. Admin Panel");
            System.out.println("Type 'exit' to quit.");

            choice = scanner.nextLine();


            switch (choice) {
                case "1" -> processAllVehiclesRequest();
                case "2" -> processGetByMakeModelRequest();
                case "3" -> processGetByYearRequest();
                case "4" -> processGetByColorRequest();
                case "5" -> processGetByMileageRequest();
                case "6" -> processGetByVehicleTypeRequest();
                case "7" -> processGetByPriceRequest();
                case "8" -> processAddVehicleRequest();
                case "9" -> processRemoveVehicleRequest();
                case "10" -> processContractRequest();
                case "11" -> processAdminPanelRequest();
                default -> {
                    if (!choice.equals("exit")) {
                        System.out.println("Invalid choice, please try again.");
                    }
                }
            }
        }
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.printf("%d %d %s %s %s %s %d %.2f\n",
                        vehicle.getVin(),
                        vehicle.getYear(),
                        vehicle.getMake(),
                        vehicle.getModel(),
                        vehicle.getVehicleType(),
                        vehicle.getColor(),
                        vehicle.getOdometer(),
                        vehicle.getPrice());
            }
        }
    }

    private void processAllVehiclesRequest() {
    }

    private void processGetByPriceRequest() {
    }

    private void processGetByMakeModelRequest() {
    }

    private void processGetByYearRequest() {
    }

    private void processGetByColorRequest() {
    }

    private void processGetByMileageRequest() {
    }

    private void processGetByVehicleTypeRequest() {
    }

    private void processAddVehicleRequest() {
    }

    private void processRemoveVehicleRequest() {
    }

    private void processContractRequest() {
    }

    private void processAdminPanelRequest() {
        System.out.println("Enter the admin password:");
        String password = scanner.nextLine();

        if (password.equals("admin")) {
            AdminUserInterface adminUI = new AdminUserInterface();
            adminUI.displayAdminMenu();
        } else {
            System.out.println("Invalid password.");
        }
    }
}
