package com.solvd.realestate.maintenence;

public enum MaintenanceType {
    PLUMBING("Plumbing", "Fix plumbing issues"),
    ELECTRICAL("Electrical", "Fix electrical issues"),
    HVAC("HVAC", "Fix heating, ventilation, and air conditioning issues"),
    PAINTING("Painting", "Perform painting work"),
    GENERAL("General Maintenance", "General maintenance tasks");

    private final String displayName;
    private final String description;

    // Static block to initialize enum constants
    static {
        System.out.println("Initializing MaintenanceType enum");
    }

    // Constructor
    MaintenanceType(String displayName, String description) {
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
    }
}
