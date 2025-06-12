package com.dealership.dao;

import com.dealership.models.LeaseContract;
import com.dealership.models.Vehicle;

import java.sql.*;
import java.util.ArrayList;

public class LeaseDao {
    private final Connection connection;

    public LeaseDao(Connection connection) {
        this.connection = connection;
    }

    // Method to insert a new lease contract
    public void insertLeaseContract(LeaseContract contract) throws SQLException {
        String query = "INSERT INTO lease_contracts (VIN, customer_name, customer_email, start_date, end_date, monthly_payment) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, contract.getVehicle().getVin());
            stmt.setString(2, contract.getCustomerName());
            stmt.setString(3, contract.getCustomerEmail());
            stmt.setDate(4, Date.valueOf(contract.getContractDate()));
            stmt.setDate(5, Date.valueOf(contract.getEndDate()));
            stmt.setDouble(6, contract.getMonthlyPayment());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                contract.setLeaseId(keys.getInt(1));
            }
        }
    }

    // Method to get all lease contracts
    public ArrayList<LeaseContract> getAllLeaseContracts() throws SQLException {
        ArrayList<LeaseContract> contracts = new ArrayList<>();
        String query = "SELECT * FROM lease_contracts";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            VehicleDao vehicleDao = new VehicleDao(connection);

            while (rs.next()) {
                Vehicle vehicle = vehicleDao.getVehicleByVin(rs.getString("VIN"));
                LeaseContract contract = new LeaseContract(
                        rs.getInt("lease_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_email"),
                        vehicle,
                        rs.getDate("start_date").toString(),
                        vehicle.getPrice(),
                        rs.getDate("end_date").toLocalDate()
                );
                contracts.add(contract);
            }
        }
        return contracts;
    }
}
