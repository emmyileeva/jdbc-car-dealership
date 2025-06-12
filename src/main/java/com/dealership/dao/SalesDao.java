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
        String query = "INSERT INTO sales_contracts (VIN, buyer_name, email, sale_date, sale_price, is_financed) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, contract.getVehicle().getVin());
            stmt.setString(2, contract.getCustomerName());
            stmt.setString(3, contract.getCustomerEmail());
            stmt.setDate(4, Date.valueOf(contract.getContractDate()));
            stmt.setDouble(5, contract.getTotalPrice());
            stmt.setBoolean(6, contract.isFinanced());

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
                        rs.getString("buyer_name"),
                        rs.getString("email"),
                        vehicle,
                        rs.getDate("sale_date").toString(),
                        rs.getDouble("sale_price"),
                        rs.getBoolean("is_financed")
                );
                contract.setContractId(rs.getInt("contract_id"));
                contracts.add(contract);
            }
        }
        return contracts;
    }
}