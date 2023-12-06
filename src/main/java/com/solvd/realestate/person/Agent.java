package com.solvd.realestate.person;

import com.solvd.realestate.interfaces.FilterAppointments;
import com.solvd.realestate.interfaces.InformationPrinting;
import com.solvd.realestate.interfaces.LocationInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.realestate.appointments.Appointment;

import java.time.LocalDateTime;

public class Agent extends Person implements InformationPrinting, LocationInfo {
    private final static Logger LOGGER = LogManager.getLogger(Agent.class);
    private static int lastAgentId = 0;
    private int agentId;
    private CityLocation cityLocation;
    private int salary;

    public Agent(String name, String surname, ContactInformation contact, CityLocation cityLocation, int salary) {
        super(name, surname, contact);
        this.agentId = ++lastAgentId;
        this.cityLocation = cityLocation;
        this.salary = salary;
    }

    public int getAgentId() {
        return agentId;
    }

    public CityLocation getCityLocation() {
        return cityLocation;
    }

    public void setCityLocation(CityLocation cityLocation) {
        this.cityLocation = cityLocation;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public void printInfo() {
        LOGGER.info("Agent name: " + this.name + " " + this.surname + "." + " Phone number: " + this.contact.getPhoneNumber() + " Email: " + this.contact.getEmail() + "Area of work: " + this.cityLocation.getCityName());
    }

    //TODO: THINK OF A DIFFERENT METHOD TO OVERRIDE SINCE IT'S NOW THE SAME FOR BOTH AGENT AND CLIENT
    @Override
    public void nearestAppointmentNotification(FilterAppointments<LocalDateTime> filter) {
        Appointment nearestAppointment = null;
        for (Appointment appointment : this.appointments) {
            if (filter.appointmentFilter(appointment, LocalDateTime.now())) {
                nearestAppointment = appointment;
                break;
            }
        }
        if (nearestAppointment != null) {
            LOGGER.info("Nearest appointment for agent is at: " + nearestAppointment.getAppointmentDateTime());
        } else {
            LOGGER.info("There are no nearest appointments");
        }
    }
}
