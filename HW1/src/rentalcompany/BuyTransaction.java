package rentalcompany;

import java.time.LocalDateTime;

public class BuyTransaction {
    private static int lastTransactionId = 0;
    private int transactionId;
    private Client client;
    private Agent agent;
    private Apartment apartment;
    private LocalDateTime transactionDateTime;
    private Bill bill;
    public BuyTransaction(Apartment apartment, Agent agent, Client client) {
        this.transactionId = lastTransactionId;
        this.transactionDateTime = LocalDateTime.now();
        this.apartment = apartment;
        this.agent = agent;
        this.client = client;
        this.bill = new Bill();
        bill.calculateBill(TransactionType.BUY,apartment.getBuyingPrice());
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
    public void printTransaction() {
        System.out.println("Buy transaction Id: " + transactionId);
        System.out.println("Parcel Id: " + apartment.getApartmentId() + " in " + apartment.getLocation().getCityName());
        System.out.println("Rented by " + client.getName()+ " " + client.getSurname());
        System.out.println("With help of agent " + agent.getName() + " " + agent.getSurname());
        System.out.println("Time of transaction: " + transactionDateTime);
        System.out.println("Total " + bill.getBill());
    }
}
