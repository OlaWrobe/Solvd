package realestate;

import realestate.apartment.House;

public class BuyTransaction extends Transaction {
    private static int lastTransactionId = 0;
    private int transactionId;

    public BuyTransaction(House house, Agent agent, Client client) {
        super(house, agent, client);
        this.transactionId = lastTransactionId;
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
    public void printTransaction() {
        System.out.println("Buy transaction Id: " + transactionId);
        System.out.println("Parcel Id: " + house.getApartmentId() + " in " + house.getLocation().getCityName());
        System.out.println("Rented by " + client.getName() + " " + client.getSurname());
        System.out.println("With help of agent " + agent.getName() + " " + agent.getSurname());
        System.out.println("Time of transaction: " + transactionDateTime);
        System.out.println("Total " + bill.getBill());
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
