package com.solvd.realestate.transactions;

import com.solvd.realestate.exceptions.InvalidTransactionTypeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Bill {
    private final static Logger LOGGER = LogManager.getLogger(Bill.class);
    private double amount;

    public Bill() {
        this.amount = 0;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double calculateBillForBuyOrRent(TransactionType transactionType, double apartmentCost) {
        if (transactionType.equals(TransactionType.BUY)) {
            this.amount = transactionType.BUY.calculateCost(apartmentCost);
        } else if (transactionType.equals(TransactionType.RENTAL)) {
            this.amount = transactionType.RENTAL.calculateCost(apartmentCost);
        } else {
            throw new InvalidTransactionTypeException("Incorrect transaction type: " + transactionType);
        }
        return this.amount;
    }
    public double calculateBillForMisc(TransactionFee typeOfService)
    {
        this.amount = typeOfService.getFee() + this.amount;
        return this.amount;
    }

    public static void printPriceList() {
        LOGGER.info(TransactionFee.printPriceList.get());
    }
}
