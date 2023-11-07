package realestate.person;

import realestate.transactions.TransactionType;

public class ClientForm {
    private double budget;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private CityLocation location;
    private boolean needsParking;
    private TransactionType transactionType;

    // Constructor
    public ClientForm(double budget, int numberOfBedrooms, int numberOfBathrooms, CityLocation location, boolean needsParking, TransactionType transactionType) {
        this.budget = budget;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.location = location;
        this.needsParking = needsParking;
        this.transactionType = transactionType;
    }

    // Getters and Setters for the fields
    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public void setNumberOfBathrooms(int numberOfBathrooms) {
        this.numberOfBathrooms = numberOfBathrooms;
    }

    public CityLocation getLocation() {
        return location;
    }

    public void setLocation(CityLocation location) {
        this.location = location;
    }

    public boolean getNeedsParking() {
        return needsParking;
    }

    public void setNeedsParking(boolean needsParking) {
        this.needsParking = needsParking;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

}
