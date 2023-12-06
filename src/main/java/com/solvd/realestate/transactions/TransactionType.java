package com.solvd.realestate.transactions;

public enum TransactionType {
    RENTAL("Rental", 0.1),
    BUY("Buy", 0.2);

    private final String description;
    private final double transactionFee;

    // Constructor
    TransactionType(String description, double transactionFee) {
        this.description = description;
        this.transactionFee = transactionFee;
    }
    // Getters
    public String getDescription() {
        return description;
    }
    public double getTransactionFee() {
        return transactionFee;
    }
    public double calculateCost(double cost) {
        return cost + cost * transactionFee;
    }
}