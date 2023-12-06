package com.solvd.realestate.person;

import com.solvd.realestate.interfaces.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.realestate.appointments.Appointment;

import java.time.LocalDateTime;

public class Client extends Person implements InformationPrinting, AppointmentHandling, RentalActions {
    private final static Logger LOGGER = LogManager.getLogger(Client.class);
    private static int lastClientId = 0;
    private int clientId;
    private ClientForm clientForm;

    // Constructor
    public Client(String name, String surname, ContactInformation contact, ClientForm clientForm) {
        super(name, surname, contact);
        this.clientId = ++lastClientId;
        this.clientForm = clientForm;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public ClientForm getClientForm() {
        return clientForm;
    }

    public void setClientForm(ClientForm clientForm) {
        this.clientForm = clientForm;
    }

    @Override
    public void printInfo() {
        String parking;
        if (this.clientForm.getNeedsParking()) {
            parking = "With parking";
        } else {
            parking = "Without parking";
        }
        LOGGER.info("Client name: " + this.name + " " + this.surname + "." + " Phone number: " + this.contact.getPhoneNumber() + " Email: " + this.contact.getEmail() + "\n"
                + "Looking for apartment for " + this.clientForm.getTransactionType() + " with "
                + this.clientForm.getNumberOfBedrooms() + " bedrooms and "
                + this.clientForm.getNumberOfBathrooms() + " bathrooms" + "\n" + parking + "Budget: " + this.clientForm.getBudget() + "\n");
    }

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
            LOGGER.info("Nearest appointment for client is at: " + nearestAppointment.getAppointmentDateTime());
        } else {
            LOGGER.info("There are no nearest appointments");
        }
    }

//    public void payRent(Apartment apartment) {
//        for (RentalTransaction transaction : rentalTransactions) {
//            if (transaction.getApartment().equals(apartment)) {
//                transaction.payRent();
//            }
//        }
//    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

}
