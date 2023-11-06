package realestate.apartment;

public interface IApartment {
        int getNumberOfBedrooms();
        int getNumberOfBathrooms();
        boolean getHasParking();
        double getRentPrice();
        double getBuyingPrice();
        void printAccommodationInfo();
}
