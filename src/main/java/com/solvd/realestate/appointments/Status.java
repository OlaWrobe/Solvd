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
    // Logger
    private static final Logger LOGGER = LogManager.getLogger(Status.class);

    static {
        LOGGER.info("Explanation of all the statuses: \n" +
                "Planned: User requested an appointment. Not confirmed by agency\n" +
                "Confirmed: Agency has confirmed the appointment.\n" +
                "Cancelled: The appointment will not be held due to cancellation\n" +
                "Completed: The appointment was successfully held\n");
    }

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    // Methods
    public void printAppointmentStatus() {
        LOGGER.info("Status Info: {}", getDescription());
    }
}