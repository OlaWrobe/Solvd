package realestate;

import java.time.LocalDateTime;

public class BuyTransaction extends Transaction {
    private static int lastTransactionId = 0;

    public BuyTransaction(Apartment apartment, Agent agent, Client client) {
        this.transactionId = lastTransactionId;
        this.transactionDateTime = LocalDateTime.now();
        this.apartment = apartment;
        this.agent = agent;
        this.client = client;
        this.bill = new Bill();
        this.bill.calculateBill(TransactionType.BUY, apartment.getBuyingPrice());
        lastTransactionId++;
    }

    // Getters and Setters
    public static int getLastTransactionId() {
        return lastTransactionId;
    }

    public static void setLastTransactionId(int lastTransactionId) {
        BuyTransaction.lastTransactionId = lastTransactionId;
    }

    @Override
    public void printTransaction() {
        System.out.println("Buy transaction Id: " + transactionId);
        System.out.println("Parcel Id: " + apartment.getApartmentId() + " in " + apartment.getLocation().getCityName());
        System.out.println("Rented by " + client.getName() + " " + client.getSurname());
        System.out.println("With help of agent " + agent.getName() + " " + agent.getSurname());
        System.out.println("Time of transaction: " + transactionDateTime);
        System.out.println("Total " + bill.getBill());
    }
}
