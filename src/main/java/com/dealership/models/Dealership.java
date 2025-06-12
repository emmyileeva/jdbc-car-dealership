package com.dealership.models;

import com.dealership.dao.VehicleDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dealership {
    private int dealershipId;
    private String name;
    private String address;
    private String phone;
    private final VehicleDao vehicleDao;

    public Dealership(int dealershipId, String name, String address, String phone, Connection connection) {
        this.dealershipId = dealershipId;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.vehicleDao = new VehicleDao(connection);
    }

    public int getDealershipId() {
        return dealershipId;
    }

    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max) throws SQLException {
        return vehicleDao.getVehiclesByPriceRange(min, max);
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) throws SQLException {
        return vehicleDao.getVehiclesByMakeAndModel(make, model);
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) throws SQLException {
        return vehicleDao.getVehiclesByYearRange(min, max);
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) throws SQLException {
        return vehicleDao.getVehiclesByColor(color);
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) throws SQLException {
        return vehicleDao.getVehiclesByMileageRange(min, max);
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) throws SQLException {
        return vehicleDao.getVehiclesByType(vehicleType);
    }

    public ArrayList<Vehicle> getAllVehicles() throws SQLException {
        return vehicleDao.getAllVehicles();
    }

    public void addVehicle(Vehicle vehicle) throws SQLException {
        vehicleDao.addVehicle(vehicle);
    }

    public void removeVehicle(String vin) throws SQLException {
        vehicleDao.removeVehicle(vin);
    }
}