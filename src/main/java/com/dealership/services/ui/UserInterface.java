package com.dealership.services.ui;

import com.dealership.dao.LeaseDao;
import com.dealership.dao.SalesDao;
import com.dealership.models.*;
import com.dealership.services.DatabaseHelper;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private final Scanner scanner = new Scanner(System.in);

    private void init() {
        try {
            Connection connection = DatabaseHelper.getConnection();
            dealership = new Dealership(0, "My Dealership", "123 Main St", "123-456-7890", connection);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
        }
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
            System.out.print("Please enter your choice: ");

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
                System.out.printf("%s %d %s %s %s %s %d %.2f\n",
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
        try {
            List<Vehicle> vehicles = dealership.getAllVehicles();
            displayVehicles(vehicles);
        } catch (SQLException e) {
            System.out.println("Error fetching all vehicles: " + e.getMessage());
        }
    }

    private void processGetByPriceRequest() {
        System.out.print("Enter min price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter max price: ");
        double max = Double.parseDouble(scanner.nextLine());

        try {
            List<Vehicle> vehicles = dealership.getVehiclesByPrice(min, max);
            displayVehicles(vehicles);
        } catch (SQLException e) {
            System.out.println("Error fetching vehicles: " + e.getMessage());
        }
    }

    private void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        try {
            List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);
            displayVehicles(vehicles);
        } catch (SQLException e) {
            System.out.println("Error fetching vehicles: " + e.getMessage());
        }
    }

    private void processGetByYearRequest() {
        System.out.print("Enter min year: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter max year: ");
        int max = Integer.parseInt(scanner.nextLine());

        try {
            List<Vehicle> vehicles = dealership.getVehiclesByYear(min, max);
            displayVehicles(vehicles);
        } catch (SQLException e) {
            System.out.println("Error fetching vehicles: " + e.getMessage());
        }
    }

    private void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        try {
            List<Vehicle> vehicles = dealership.getVehiclesByColor(color);
            displayVehicles(vehicles);
        } catch (SQLException e) {
            System.out.println("Error fetching vehicles: " + e.getMessage());
        }
    }

    private void processGetByMileageRequest() {
        System.out.print("Enter min mileage: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter max mileage: ");
        int max = Integer.parseInt(scanner.nextLine());

        try {
            List<Vehicle> vehicles = dealership.getVehiclesByMileage(min, max);
            displayVehicles(vehicles);
        } catch (SQLException e) {
            System.out.println("Error fetching vehicles: " + e.getMessage());
        }
    }

    private void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        try {
            List<Vehicle> vehicles = dealership.getVehiclesByType(type);
            displayVehicles(vehicles);
        } catch (SQLException e) {
            System.out.println("Error fetching vehicles: " + e.getMessage());
        }
    }

    private void processAddVehicleRequest() {
        System.out.print("Enter VIN: ");
        String vin = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter make: ");
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        System.out.print("Enter type: ");
        String type = scanner.nextLine();
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
        System.out.print("Enter odometer: ");
        int odometer = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);

        try {
            dealership.addVehicle(vehicle);
            System.out.println("Vehicle added successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to add vehicle: " + e.getMessage());
        }
    }

    private void processRemoveVehicleRequest() {
        System.out.print("Enter VIN of vehicle to remove: ");
        String vin = scanner.nextLine();

        try {
            dealership.removeVehicle(vin);
            System.out.println("Vehicle removed successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to remove vehicle: " + e.getMessage());
        }
    }

    private void processContractRequest() {
        try {
            System.out.print("Enter VIN of vehicle to sell or lease: ");
            String vin = scanner.nextLine();

            Vehicle vehicle = dealership.getAllVehicles().stream()
                    .filter(v -> v.getVin().equalsIgnoreCase(vin))
                    .findFirst()
                    .orElse(null);

            if (vehicle == null) {
                System.out.println("Vehicle not found.");
                return;
            }

            System.out.print("Enter customer name: ");
            String name = scanner.nextLine();

            System.out.print("Enter customer email: ");
            String email = scanner.nextLine();

            System.out.print("Is this a (1) Sale or (2) Lease? ");
            String type = scanner.nextLine();

            Connection connection = DatabaseHelper.getConnection();

            if (type.equals("1")) {
                System.out.print("Is the purchase financed? (yes/no): ");
                boolean financed = scanner.nextLine().equalsIgnoreCase("yes");

                SalesContract salesContract = new SalesContract(name, email, vehicle, LocalDate.now().toString(), vehicle.getPrice(), financed);
                SalesDao salesDao = new SalesDao(connection);
                salesDao.insertSalesContract(salesContract);

                vehicle.setSold(true);
                System.out.println("Sales contract recorded.");
            } else if (type.equals("2")) {
                System.out.print("Enter lease end date (yyyy-mm-dd): ");
                LocalDate endDate = LocalDate.parse(scanner.nextLine());

                System.out.print("Enter monthly payment: ");
                double monthlyPayment = Double.parseDouble(scanner.nextLine());

                LeaseContract leaseContract = new LeaseContract(name, email, vehicle, LocalDate.now().toString(), vehicle.getPrice(), endDate);
                LeaseDao leaseDao = new LeaseDao(connection);
                leaseDao.insertLeaseContract(leaseContract);

                vehicle.setSold(true);
                System.out.println("Lease contract recorded.");
            } else {
                System.out.println("Invalid option. Contract not created.");
            }

        } catch (Exception e) {
            System.out.println("Error processing contract: " + e.getMessage());
        }
    }

    private void processAdminPanelRequest() {
        System.out.print("Enter the admin password: ");
        String password = scanner.nextLine();

        if (password.equals("admin")) {
            AdminUserInterface adminUI = new AdminUserInterface();
            adminUI.displayAdminMenu();
        } else {
            System.out.println("Invalid password.");
        }
    }
}
