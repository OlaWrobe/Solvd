package realestate.person;

import realestate.interfaces.LocationInfo;
import realestate.transactions.TransactionType;

public class ClientForm implements LocationInfo {
    private double budget;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private CityLocation cityLocation;
    private boolean needsParking;
    private TransactionType transactionType;

    // Constructor
    public ClientForm(double budget, int numberOfBedrooms, int numberOfBathrooms, CityLocation cityLocation, boolean needsParking, TransactionType transactionType) {
        this.budget = budget;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.cityLocation = cityLocation;
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

    @Override
    public CityLocation getCityLocation() {
        return cityLocation;
    }

    public void setCityLocation(CityLocation cityLocation) {
        this.cityLocation = cityLocation;
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
