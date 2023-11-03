package realestate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {
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
        bill.calculateBill(TransactionType.RENTAL, apartment.getRentPrice());
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
        System.out.println("Parcel Id: " + apartment.getApartmentId() + " in " + apartment.getLocation().getCityName());
        System.out.println(" Client: " + client.getName() + " " + client.getSurname());
        System.out.println("With help of agent " + agent.getName() + " " + agent.getSurname());
        System.out.println("Time of transaction: " + transactionDateTime);
        System.out.println("Total " + bill.getBill());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Transaction other = (Transaction) obj;

        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;

        if (agent == null) {
            if (other.agent != null)
                return false;
        } else if (!agent.equals(other.agent))
            return false;

        if (apartment == null) {
            if (other.apartment != null)
                return false;
        } else if (!apartment.equals(other.apartment))
            return false;

        if (transactionDateTime == null) {
            if (other.transactionDateTime != null)
                return false;
        } else if (!transactionDateTime.equals(other.transactionDateTime))
            return false;

        if (bill == null) {
            if (other.bill != null)
                return false;
        } else if (!bill.equals(other.bill))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (client != null ? client.hashCode() : 0);
        result = prime * result + (agent != null ? agent.hashCode() : 0);
        result = prime * result + (apartment != null ? apartment.hashCode() : 0);
        result = prime * result + (transactionDateTime != null ? transactionDateTime.hashCode() : 0);
        result = prime * result + (bill != null ? bill.hashCode() : 0);
        return result;
    }

}
