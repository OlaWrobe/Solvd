package com.solvd.realestate.interfaces;

import com.solvd.realestate.transactions.TransactionType;

public interface Billing {
    public double getAmount();

    public double calculateBill(TransactionType transactionType, double apartmentCost);

}
