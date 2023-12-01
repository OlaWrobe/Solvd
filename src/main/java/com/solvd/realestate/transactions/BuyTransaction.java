package com.solvd.realestate.transactions;

import com.solvd.realestate.apartment.Apartment;
import com.solvd.realestate.person.Agent;
import com.solvd.realestate.person.Client;

public class BuyTransaction extends Transaction {
    private static int lastTransactionId = 0;
    private int transactionId;

    public BuyTransaction(Apartment apartment, Agent agent, Client client) {
        super(apartment, agent, client);
        this.transactionId = lastTransactionId;
        this.bill.calculateBillForBuyOrRent(TransactionType.BUY, apartment.getBuyingPrice());
        lastTransactionId++;
    }
    // Getters and Setters
    public static int getLastTransactionId() {
        return lastTransactionId;
    }

    public static void setLastTransactionId(int lastTransactionId) {
        BuyTransaction.lastTransactionId = lastTransactionId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public String toString() {
        return "Buy transaction Id: " + transactionId + "\n"
                + "Parcel Id: " + apartment.getApartmentId() + " in " + apartment.getLocation().getCityName() + "\n"
                + "Bought by " + client.getName() + " " + client.getSurname() + "\n"
                + "With help of agent " + agent.getName() + " " + agent.getSurname() + "\n"
                + "Time of transaction: " + transactionDateTime + "\n"
                + "Total " + this.getBill().getAmount();
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (!super.equals(obj))
            return false;
        BuyTransaction other = (BuyTransaction) obj;
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        return result;
    }

}
