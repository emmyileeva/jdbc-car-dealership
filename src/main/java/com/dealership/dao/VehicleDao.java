package com.dealership.dao;

import com.dealership.models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDao {
    private final Connection connection;

    public VehicleDao(Connection connection) {
        this.connection = connection;
    }

    // Get vehicles by price range
    public ArrayList<Vehicle> getVehiclesByPriceRange(double minPrice, double maxPrice) throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, minPrice);
            stmt.setDouble(2, maxPrice);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapResultSetToVehicle(rs));
            }
        }
        return vehicles;
    }

    // Get vehicles by make and model
    public ArrayList<Vehicle> getVehiclesByMakeAndModel(String make, String model) throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE make = ? AND model = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, make);
            stmt.setString(2, model);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapResultSetToVehicle(rs));
            }
        }
        return vehicles;
    }

    // Get vehicles by year range
    public ArrayList<Vehicle> getVehiclesByYearRange(int startYear, int endYear) throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, startYear);
            stmt.setInt(2, endYear);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapResultSetToVehicle(rs));
            }
        }
        return vehicles;
    }

    // Get vehicles by color
    public ArrayList<Vehicle> getVehiclesByColor(String color) throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE color = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, color);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapResultSetToVehicle(rs));
            }
        }
        return vehicles;
    }

    // Get vehicles by mileage range
    public ArrayList<Vehicle> getVehiclesByMileageRange(int minMileage, int maxMileage) throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, minMileage);
            stmt.setInt(2, maxMileage);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapResultSetToVehicle(rs));
            }
        }
        return vehicles;
    }

    // Get vehicles by type
    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles WHERE vehicleType = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vehicleType);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                vehicles.add(mapResultSetToVehicle(rs));
            }
        }
        return vehicles;
    }

    // Grab all vehicles from the database
    public ArrayList<Vehicle> getAllVehicles() throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                vehicles.add(mapResultSetToVehicle(rs));
            }
        }
        return vehicles;
    }

    // Add a new vehicle to the database
    public void addVehicle(Vehicle vehicle) throws SQLException {
        String query = "INSERT INTO vehicles (VIN, year, make, model, vehicleType, color, odometer, price, sold) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vehicle.getVin());
            stmt.setInt(2, vehicle.getYear());
            stmt.setString(3, vehicle.getMake());
            stmt.setString(4, vehicle.getModel());
            stmt.setString(5, vehicle.getVehicleType());
            stmt.setString(6, vehicle.getColor());
            stmt.setInt(7, vehicle.getOdometer());
            stmt.setDouble(8, vehicle.getPrice());
            stmt.setBoolean(9, vehicle.isSold());

            stmt.executeUpdate();
        }
    }

    // Remove a vehicle from the database
    public void removeVehicle(String vin) throws SQLException {
        String query = "DELETE FROM vehicles WHERE VIN = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vin);
            stmt.executeUpdate();
        }
    }

    // Grab vehicle by VIN
    public Vehicle getVehicleByVin(String vin) throws SQLException {
        String query = "SELECT * FROM vehicles WHERE VIN = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToVehicle(rs);
            } else {
                return null; // No vehicle found with the given VIN
            }
        }
    }

    // Helper method to map ResultSet to Vehicle object
    private Vehicle mapResultSetToVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(
                rs.getString("VIN"),
                rs.getInt("year"),
                rs.getString("make"),
                rs.getString("model"),
                rs.getString("vehicleType"),
                rs.getString("color"),
                rs.getInt("odometer"),
                rs.getDouble("price"),
                rs.getBoolean("sold")
        );
    }
}