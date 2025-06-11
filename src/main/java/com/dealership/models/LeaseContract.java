package com.dealership.models;

import java.time.LocalDate;

public class LeaseContract extends Contract {
    private int leaseId;
    private double expectedEndingValue;
    private double leaseFee;
    private double vehiclePrice;
    private LocalDate endDate;

    public LeaseContract(int leaseId, String customerName, String customerEmail, Vehicle vehicle, String contractDate, double vehiclePrice, LocalDate endDate) {
        super(customerName, customerEmail, vehicle, contractDate);
        this.leaseId = leaseId;
        this.expectedEndingValue = vehiclePrice * 0.5; // expected ending value is 50% of vehicle price
        this.leaseFee = vehiclePrice * 0.07; // lease fee is 7% of vehicle price
        this.vehiclePrice = vehiclePrice;
        this.endDate = endDate;
    }

    // Overloaded constructor for creating new contracts (no leaseId yet)
    public LeaseContract(String customerName, String customerEmail, Vehicle vehicle,
                         String contractDate, double vehiclePrice, LocalDate endDate) {
        this(0, customerName, customerEmail, vehicle, contractDate, vehiclePrice, endDate);
    }

    public int getLeaseId() {
        return leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId = leaseId;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public double getTotalPrice() {
        return expectedEndingValue + leaseFee;
    }

    @Override
    public double getMonthlyPayment() {
        return (getTotalPrice() * 0.04) / 36; // all lease contracts are 36 months at 4% interest
    }
}
