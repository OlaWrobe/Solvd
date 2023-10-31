package realestate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentalTransaction {
    private static int lastTransactionId = 0;
    private int transactionId;
    private LocalDateTime transactionDateTime;
    private Apartment apartment;
    private Agent agent;
    private Client client;
    private LocalDate rentStartDate;
    private LocalDate rentEndDate;
    private Bill bill;
    // Constructor
    public RentalTransaction(Apartment apartment, Agent agent, Client client,
                             LocalDate rentStartDate, LocalDate rentEndDate) {
        this.transactionId = lastTransactionId;
        this.transactionDateTime = LocalDateTime.now();
        this.apartment = apartment;
        this.agent = agent;
        this.client = client;
        this.rentStartDate = rentStartDate;
        this.rentEndDate = rentEndDate;
        this.bill = new Bill();
        bill.calculateBill(TransactionType.RENTAL,apartment.getRentPrice());
        lastTransactionId++;
    }
    // Getters and Setters
    public static int getLastTransactionId() {
        return lastTransactionId;
    }

    public static void setLastTransactionId(int lastTransactionId) {
        RentalTransaction.lastTransactionId = lastTransactionId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(LocalDate rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public LocalDate getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(LocalDate rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
    public void printTransaction() {
        System.out.println("Rent Transaction Id: " + transactionId);
        System.out.println("Parcel Id: " + apartment.getApartmentId() + " in " + apartment.getLocation().getCityName());
        System.out.println("Rented by " + client.getName()+ " " + client.getSurname());
        System.out.println("With help of agent " + agent.getName() + " " + agent.getSurname());
        System.out.println("Time of transaction: " + transactionDateTime);
        System.out.println("Rent start date: " + rentStartDate + " Rent end date: " + rentEndDate);
        System.out.println("Total " + bill.getBill());
    }
}
