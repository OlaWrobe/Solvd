package realestate.interfaces;

import realestate.transactions.TransactionType;

public interface Billing {
    public double getBill();

    public double calculateBill(TransactionType transactionType, double apartmentCost);

}
