package com.dealership.dao;

import com.dealership.models.SalesContract;
import com.dealership.models.Vehicle;

import java.sql.*;
import java.util.ArrayList;

public class SalesDao {
    private final Connection connection;

    public SalesDao(Connection connection) {
        this.connection = connection;
    }

    // Method to insert a new sales contract
    public void insertSalesContract(SalesContract contract) throws SQLException {
        String query = "INSERT INTO sales_contracts (VIN, customer_name, customer_email, contract_date, vehicle_price, sales_tax, recording_fee, processing_fee, financed) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, contract.getVehicle().getVin());
            stmt.setString(2, contract.getCustomerName());
            stmt.setString(3, contract.getCustomerEmail());
            stmt.setDate(4, Date.valueOf(contract.getContractDate()));
            stmt.setDouble(5, contract.getVehiclePrice());
            stmt.setDouble(6, contract.getSalesTaxAmount());
            stmt.setDouble(7, contract.getRecordingFee());
            stmt.setDouble(8, contract.getProcessingFee());
            stmt.setBoolean(9, contract.isFinanced());

            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            if (keys.next()) {
                contract.setContractId(keys.getInt(1));
            }
        }
    }

    // Method to get all sales contracts
    public ArrayList<SalesContract> getAllSalesContracts() throws SQLException {
        ArrayList<SalesContract> contracts = new ArrayList<>();
        String query = "SELECT * FROM sales_contracts";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                VehicleDao vehicleDao = new VehicleDao(connection);
                Vehicle vehicle = vehicleDao.getVehicleByVin(rs.getString("VIN")); // Get vehicle from db

                SalesContract contract = new SalesContract(
                        rs.getInt("contract_id"),
                        rs.getString("customer_name"),
                        rs.getString("customer_email"),
                        vehicle,
                        rs.getDate("contract_date").toString(),
                        rs.getDouble("vehicle_price"),
                        rs.getBoolean("financed")
                );
                contract.setSalesTaxAmount(rs.getDouble("sales_tax"));
                contract.setRecordingFee(rs.getDouble("recording_fee"));
                contract.setProcessingFee(rs.getDouble("processing_fee"));

                contracts.add(contract);
            }
        }
        return contracts;
    }
}