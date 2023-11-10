package realestate.apartment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realestate.person.CityLocation;
import realestate.interfaces.InformationPrinting;

public class Apartment implements InformationPrinting {
    private final static Logger LOGGER = LogManager.getLogger(Apartment.class);
    private static int lastApartmentId = 0;
    private int apartmentId;
    private CityLocation location;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private boolean hasParking;
    private double rentPrice;
    private double buyingPrice;

    // Constructor
    public Apartment(CityLocation location, int numberOfBedrooms, int numberOfBathrooms, boolean hasParking, double rentPrice, double buyingPrice) {
        this.apartmentId = ++lastApartmentId;
        this.location = location;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.hasParking = hasParking;
        this.rentPrice = rentPrice;
        this.buyingPrice = buyingPrice;
    }

    // Getters and setters


    public static int getLastApartmentId() {
        return lastApartmentId;
    }

    public static void setLastApartmentId(int lastApartmentId) {
        Apartment.lastApartmentId = lastApartmentId;
    }

    public int getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(int apartmentId) {
        this.apartmentId = apartmentId;
    }

    public CityLocation getLocation() {
        return location;
    }

    public void setLocation(CityLocation location) {
        this.location = location;
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

    public boolean getHasParking() {
        return hasParking;
    }

    public void setHasParking(boolean hasParking) {
        this.hasParking = hasParking;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public double getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public void printInfo() {
        String parking;
        if (hasParking) {
            parking = " With parking.";
        } else {
            parking = " Without parking.";
        }

        LOGGER.info("Apartment number " + this.apartmentId + " in " + this.location.getCityName() + "\n" +
                "No. of rooms: " + this.numberOfBedrooms + " No. of bathrooms " + this.numberOfBathrooms + "." + "\n"
                + " Buying price: " + this.buyingPrice + " Rent price: " + this.rentPrice + "\n");
    }
}