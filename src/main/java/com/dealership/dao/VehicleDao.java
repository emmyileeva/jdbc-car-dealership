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

    // Grab all vehicles from the database
    public ArrayList<Vehicle> getAllVehicles() throws SQLException {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM vehicles";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vehicle vehicle = new Vehicle(
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
                vehicles.add(vehicle);
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
}