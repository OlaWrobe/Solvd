package realestate.person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realestate.appointments.Appointment;
import realestate.interfaces.InformationPrinting;
import realestate.interfaces.LocationInfo;
import realestate.transactions.Bill;

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

    @Override
    public void nearestAppointmentNotification() {
        Appointment nearestAppointment = null;
        LocalDateTime now = LocalDateTime.now();

        for (Appointment appointment : this.appointments) {
            if (appointment.getAppointmentDateTime().isAfter(now)) {
                if (nearestAppointment == null || appointment.getAppointmentDateTime().isBefore(nearestAppointment.getAppointmentDateTime())) {
                    nearestAppointment = appointment;
                }
            }
        }

        if (nearestAppointment != null) {
            LOGGER.info("Nearest appointment for agent is at: " + nearestAppointment.getAppointmentDateTime());
        } else {
            LOGGER.info("There are no nearest appointments");
        }
    }
}
