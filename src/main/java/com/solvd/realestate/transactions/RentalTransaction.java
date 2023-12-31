package com.solvd.realestate.transactions;

import com.solvd.realestate.apartment.Apartment;
import com.solvd.realestate.person.Agent;
import com.solvd.realestate.person.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RentalTransaction extends Transaction {
    private static int lastTransactionId = 0;
    private int transactionId;
    private LocalDate rentStartDate;
    private LocalDate rentEndDate;
    private LocalDate lastRentPayment;

    // Constructor
    public RentalTransaction(Apartment apartment, Agent agent, Client client, LocalDate rentStartDate, LocalDate rentEndDate) {
        super(apartment, agent, client);
        this.transactionId = lastTransactionId;
        this.rentStartDate = rentStartDate;
        this.rentEndDate = rentEndDate;
        this.bill.calculateBillForBuyOrRent(TransactionType.RENTAL, apartment.getRentPrice());
        this.lastRentPayment = null;
        lastTransactionId++;
    }

    // Getters and Setters
    public static int getLastTransactionId() {
        return lastTransactionId;
    }

    public static void setLastTransactionId(int lastTransactionId) {
        RentalTransaction.lastTransactionId = lastTransactionId;
    }

    public LocalDate getLastRentPayment() {
        return lastRentPayment;
    }

    public void setLastRentPayment(LocalDate lastRentPayment) {
        this.lastRentPayment = lastRentPayment;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(LocalDate rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public LocalDate getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(LocalDate rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    //Methods
    public void payRent() {
        this.lastRentPayment = LocalDate.now();
    }

    private int calculateMonthsDue() {
        if (lastRentPayment == null) {
            return (int) ChronoUnit.MONTHS.between(rentStartDate, LocalDateTime.now()) + 1;
        } else {
            return (int) ChronoUnit.MONTHS.between(lastRentPayment, LocalDateTime.now());
        }
    }

    public double calculateRent() {
        double monthlyRent = this.bill.getAmount();
        int monthsDue = calculateMonthsDue();
        return monthlyRent * monthsDue;

    }

    @Override
    public String toString() {
        return "Rent Transaction Id: " + this.transactionId + "\n"
                + "Parcel Id: " + apartment.getApartmentId() + " in " + apartment.getLocation().getCityName() + "\n"
                + "Rented by " + client.getName() + " " + client.getSurname() + "\n"
                + "With help of agent " + agent.getName() + " " + agent.getSurname() + "\n"
                + "Time of transaction: " + transactionDateTime + "\n"
                + "Rent start date: " + rentStartDate + " Rent end date: " + rentEndDate + "\n"
                + "Total " + this.bill.getAmount();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (!super.equals(obj))
            return false;

        RentalTransaction other = (RentalTransaction) obj;

        if (rentStartDate == null) {
            if (other.rentStartDate != null)
                return false;
        } else if (!rentStartDate.equals(other.rentStartDate))
            return false;

        if (rentEndDate == null) {
            if (other.rentEndDate != null)
                return false;
        } else if (!rentEndDate.equals(other.rentEndDate))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (rentStartDate != null ? rentStartDate.hashCode() : 0);
        result = prime * result + (rentEndDate != null ? rentEndDate.hashCode() : 0);
        return result;
    }

}
