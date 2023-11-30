package com.solvd.realestate.maintenence;
public enum MaintenanceType {
    PLUMBING("Plumbing", "Fix plumbing issues", 2.5),
    ELECTRICAL("Electrical", "Fix electrical issues", 3.0),
    HVAC("HVAC", "Fix heating, ventilation, and air conditioning issues", 4.0),
    PAINTING("Painting", "Perform painting work", 1.5),
    GENERAL("General Maintenance", "General maintenance tasks", 2.0);

    private final String displayName;
    private final String description;
    private final double timeNeeded; // in hours
    // Constructor
    MaintenanceType(String displayName, String description, double timeNeeded) {
        this.displayName = displayName;
        this.description = description;
        this.timeNeeded = timeNeeded;
    }

    // Getter method for display name
    public String getDisplayName() {
        return displayName;
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    // Getter method for time needed
    public double getTimeNeeded() {
        return timeNeeded;
    }

    // Example method in the enum
    public String getFullInfo() {
        return String.format("%s - %s (Time Needed: %.2f hours)", displayName, description, timeNeeded);
    }

    // Method to calculate maintenance cost based on time and hourly rate
    public double calculateCost(double hourlyRate) {
        return timeNeeded * hourlyRate;
    }
}
