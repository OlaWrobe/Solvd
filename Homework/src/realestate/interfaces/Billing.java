package realestate.interfaces;

import realestate.transactions.TransactionType;

public interface Billing {
    public double getAmount();

    public double calculateBill(TransactionType transactionType, double apartmentCost);

}
