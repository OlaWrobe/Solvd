package com.solvd.realestate.transactions;

public enum TransactionType {
    RENTAL("Rental", 0.1),
    BUY("Buy", 0.2);

    private final String description;
    private final double transactionFee;

    // Static block to initialize enum constants
    static {
        System.out.println("Initializing TransactionType enum");
    }

    // Constructor
    TransactionType(String description, double transactionFee) {
        this.description = description;
        this.transactionFee = transactionFee;
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    // Getter method for transaction fee
    public double getTransactionFee() {
        return transactionFee;
    }

    // Example method in the enum
    public double calculateCost(double cost) {
        return cost * transactionFee;
    }
}