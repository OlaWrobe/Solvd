package com.solvd.realestate.transactions;

public enum TransactionFee {
    CONSULTATION(500),
    TENANT_REPRESENTATION(200),
    FIT_OUT(700);

    private final double fee;

    // Constructor
    TransactionFee(double fee) {
        this.fee = fee;
    }
    // Getter method for the fee
    public double getFee() {
        return fee;
    }
    //Methods
}
