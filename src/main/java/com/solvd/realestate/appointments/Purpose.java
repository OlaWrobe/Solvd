package com.solvd.realestate.appointments;

public enum Purpose {
    BUY("Buy Property", "Initiate a property purchase"),
    RENTAL("Rent Property", "Initiate a property rental"),
    CONSULTATION("Consultation", "Request a consultation"),
    PAYMENT("Payment", "Make a payment"),
    ISSUE("Report Issue", "Report an issue"),
    OTHER("Other", "Other purposes");

    private final String displayName;
    private final String description;

    // Static block to initialize enum constants
    static {
        System.out.println("Initializing Purpose enum");
    }

    // Constructor
    Purpose(String displayName, String description) {
        this.displayName = displayName;
        this.description = description;
    }

    // Getter method for display name
    public String getDisplayName() {
        return displayName;
    }

    // Getter method for description
    public String getDescription() {
        return description;
    }

    // Example method in the enum
    public String getFullInfo() {
        return String.format("%s - %s", displayName, description);
    }}