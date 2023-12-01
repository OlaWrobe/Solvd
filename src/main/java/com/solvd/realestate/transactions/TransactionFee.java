package com.solvd.realestate.transactions;

import java.util.function.Supplier;

public enum TransactionFee {
    CONSULTATION(500, "Consultation Fee"),
    TENANT_REPRESENTATION(200, "Tenant Representation Fee"),
    FIT_OUT(700, "Fit Out Fee");

    private final double fee;
    private final String description;

    // Constructor
    TransactionFee(double fee, String description) {
        this.fee = fee;
        this.description = description;
    }

    // Getters
    public double getFee() {
        return fee;
    }

    public String getDescription() {
        return description;
    }

    //methods
    public static Supplier<String> printPriceList = () -> {
        String result = "Transaction Fee Price List:";
        for (TransactionFee fee : TransactionFee.values()) {
            result += fee.getDescription() + ": $" + fee.getFee() + "\n";
        }
        return result;
    };
}

