package realestate.transactions;

import realestate.agency.Bill;
import realestate.apartment.Apartment;
import realestate.person.Agent;
import realestate.person.Client;

import java.time.LocalDateTime;

public class Transaction {
    private final double fee = 0.10;
    protected Client client;
    protected Agent agent;
    protected Apartment apartment;
    protected LocalDateTime transactionDateTime;
    protected Bill bill;

    public Transaction(Apartment apartment, Agent agent, Client client) {
        this.transactionDateTime = LocalDateTime.now();
        this.apartment = apartment;
        this.agent = agent;
        this.client = client;
        this.bill = new Bill();
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

    public final double calculateTransactionFee() {
        double transactionFee = this.bill.getBill() + this.bill.getBill() * this.fee;
        return transactionFee;
    }

    @Override
    public String toString() {
        return "Parcel Id: " + apartment.getApartmentId() + " in " + apartment.getLocation().getCityName() + "\n"
                + " Client: " + client.getName() + " " + client.getSurname() + "\n"
                + "With help of agent " + agent.getName() + " " + agent.getSurname() + "\n"
                + "Time of transaction: " + transactionDateTime + "\n"
                + "Total " + this.calculateTransactionFee();
    }

}
