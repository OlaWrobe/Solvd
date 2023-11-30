package com.solvd.realestate.appointments;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    PLANNED("Planned"),
    CONFIRMED("Confirmed"),
    CANCELLED("Cancelled"),
    COMPLETED("Completed");

    private final String description;

    // Map to store additional information related to each status
    private static Map<Status, String> statusInfo = new HashMap<>();

    // Logger
    private static final Logger LOGGER = LogManager.getLogger(Status.class);

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // Method to get additional information related to the status
    public String getStatusInfo() {
        return statusInfo.get(this);
    }

    // Method to print status information for a specific appointment
    public void printAppointmentStatus(int appointmentNumber) {
        LOGGER.info("Appointment number {} has been {}.", appointmentNumber, this.getDescription());
        LOGGER.info("Status Info: {}", getStatusInfo());
    }
}