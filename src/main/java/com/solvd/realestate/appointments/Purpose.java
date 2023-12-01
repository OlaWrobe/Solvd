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
    private final double defaultDuration;

    // Constructor
    Purpose(String displayName, String description, double defaultDuration) {
        this.displayName = displayName;
        this.description = description;
        this.defaultDuration = defaultDuration;
    }

    // Getters
    public String getDisplayName() {
        return displayName;
    }

    public String getDescription() {
        return description;
    }

    public double getDefaultDuration() {
        return defaultDuration;
    }

    // Methods
    public double calculateCost(double baseCost, double overtime) {
        switch (this) {
            case BUY:
                if (overtime < 0.5) {
                    return baseCost * 1.2;
                } else {
                    return baseCost * 1.3;
                }
            case RENTAL:
                if (overtime < 0.5) {
                    return baseCost * 1.1;
                } else {
                    return baseCost * 1.2;
                }
            default:
                return baseCost * 1.05;
        }
    }
}
