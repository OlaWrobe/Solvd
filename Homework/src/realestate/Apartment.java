package realestate;

public class Apartment {
    private static int lastApartmentId = 0;
    private int ApartmentId;
    private CityLocation location;
    private int numberOfBedrooms;
    private int numberOfBathrooms;
    private boolean hasParking;
    private double rentPrice;
    private double buyingPrice;

    // Constructor
    public Apartment(CityLocation location, int numberOfBedrooms, int numberOfBathrooms, boolean hasParking, double rentPrice, double buyingPrice) {
        this.ApartmentId = ++lastApartmentId;
        this.location = location;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.hasParking = hasParking;
        this.rentPrice = rentPrice;
        this.buyingPrice = buyingPrice;
    }

    // Getters and setters
    public int getApartmentId() {
        return ApartmentId;
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
}
