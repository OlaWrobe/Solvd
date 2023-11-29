package com.solvd.realestate.apartment;

public enum ApartmentType {
    ONE_BEDROOM("One Bedroom", 1, 1, 1),
    TWO_BEDROOM("Two Bedroom", 2, 1, 1),
    THREE_BEDROOM("Three Bedroom", 3, 2, 2);

    private final String description;
    private final int numberOfBedrooms;
    private final int numberOfBathrooms;
    private final int numberOfLivingRooms;

    // Constructor
    ApartmentType(String description, int numberOfBedrooms, int numberOfBathrooms, int numberOfLivingRooms) {
        this.description = description;
        this.numberOfBedrooms = numberOfBedrooms;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfLivingRooms = numberOfLivingRooms;
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    // Getter methods for apartment features
    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public int getNumberOfBathrooms() {
        return numberOfBathrooms;
    }

    public int getNumberOfLivingRooms() {
        return numberOfLivingRooms;
    }

    // Example method in the enum
    public String getFullInfo() {
        return String.format("%s - Bedrooms: %d, Bathrooms: %d, Living Rooms: %d",
                description, numberOfBedrooms, numberOfBathrooms, numberOfLivingRooms);
    }
}