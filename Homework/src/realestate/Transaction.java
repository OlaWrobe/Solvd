package realestate;

import realestate.apartment.House;

import java.time.LocalDateTime;

public class Transaction {
    protected Client client;
    protected Agent agent;
    protected House house;
    protected LocalDateTime transactionDateTime;
    protected Bill bill;

    public Transaction(House house, Agent agent, Client client) {
        this.transactionDateTime = LocalDateTime.now();
        this.house = house;
        this.agent = agent;
        this.client = client;
        this.bill = new Bill();
        bill.calculateBill(TransactionType.RENTAL, house.getRentPrice());
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

    public House getApartment() {
        return house;
    }

    public void setApartment(House house) {
        this.house = house;
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
        System.out.println("Parcel Id: " + house.getApartmentId() + " in " + house.getLocation().getCityName());
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

        if (house == null) {
            if (other.house != null)
                return false;
        } else if (!house.equals(other.house))
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
        result = prime * result + (house != null ? house.hashCode() : 0);
        result = prime * result + (transactionDateTime != null ? transactionDateTime.hashCode() : 0);
        result = prime * result + (bill != null ? bill.hashCode() : 0);
        return result;
    }

}
