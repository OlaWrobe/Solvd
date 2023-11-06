package realestate.apartment;

import realestate.CityLocation;

public class Flat implements IApartment {
    private static int lastFlatId = 0;
    private int flatId;
    private CityLocation location;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private boolean hasParking;
    private double rentPrice;
    private double buyingPrice;

    // Constructor
    public Flat(CityLocation location, int numberOfBedrooms, int numberOfBathrooms, boolean hasParking, double rentPrice, double buyingPrice) {
        this.flatId = ++lastFlatId;
        this.location = location;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.hasParking = hasParking;
        this.rentPrice = rentPrice;
        this.buyingPrice = buyingPrice;
    }

    // Implementing interface methods
    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public boolean getHasParking() {
        return hasParking;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void printAccommodationInfo() {
        System.out.println("Flat in " + this.location.getCityName());
        System.out.print("No. of rooms: " + this.numberOfBedrooms + " No. of bathrooms " + this.numberOfBathrooms + ".");
        if (hasParking) {
            System.out.print(" With parking.");
        } else {
            System.out.print(" Without parking.");
        }
        System.out.println(" Buying price: " + this.buyingPrice + " Rent price: " + this.rentPrice);
    }}