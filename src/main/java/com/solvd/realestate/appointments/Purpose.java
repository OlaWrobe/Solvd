package com.solvd.realestate.appointments;

public enum Purpose {
    BUY("Buy Property", "Initiate a property purchase", 1.5),
    RENTAL("Rent Property", "Initiate a property rental", 1.5),
    CONSULTATION("Consultation", "Request a consultation", 1.0),
    PAYMENT("Payment", "Make a payment", 0.5),
    ISSUE("Report Issue", "Report an issue", 1.0),
    OTHER("Other", "Other purposes", 1.0);

    private final String displayName;
    private final String description;
    private final double defaultDuration; // in hours

    // Static block to initialize enum constants
    static {
        System.out.println("Initializing Purpose enum");
    }

    // Constructor
    Purpose(String displayName, String description, double defaultDuration) {
        this.displayName = displayName;
        this.description = description;
        this.defaultDuration = defaultDuration;
    }

    // Getter method for display name
    public String getDisplayName() {
        return displayName;
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    // Getter method for default duration
    public double getDefaultDuration() {
        return defaultDuration;
    }

    // Example method in the enum
    public String getFullInfo() {
        return String.format("%s - %s (Default Duration: %.2f hours)", displayName, description, defaultDuration);
    }

    // Method to calculate appointment duration based on purpose
    public double calculateDuration(double customMultiplier) {
        return defaultDuration * customMultiplier;
    }

    // Static method to print the purpose list
    public static void printPurposeList() {
        System.out.println("Purpose List:");
        for (Purpose purpose : Purpose.values()) {
            System.out.println(purpose.getFullInfo());
        }
    }
    public double calculateCost(double baseCost) {
        // Add custom cost calculation based on purpose
        switch (this) {
            case BUY:
                return baseCost * 1.2;
            case RENTAL:
                return baseCost * 1.1;
            default:
                return baseCost;
        }
    }

    public double calculateCustomDuration(double customMultiplier) {
        // Add custom duration calculation based on purpose
        return getDefaultDuration() * customMultiplier;
    }

    public String getCustomInfo(double customMultiplier) {
        // Additional information based on custom multiplier
        return String.format("%s - %s (Default Duration: %.2f hours, Custom Duration: %.2f hours)",
                getDisplayName(), getDescription(), getDefaultDuration(), calculateCustomDuration(customMultiplier));
    }
}
