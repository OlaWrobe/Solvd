package realestate.person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realestate.apartment.Apartment;
import realestate.appointments.Appointment;
import realestate.appointments.Status;
import realestate.interfaces.AppointmentHandling;
import realestate.interfaces.InformationPrinting;
import realestate.interfaces.RentalActions;
import realestate.transactions.RentalTransaction;

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
    public void nearestAppointmentNotification() {
        Appointment nearestAppointment = null;
        LocalDateTime now = LocalDateTime.now();

        for (Appointment appointment : this.appointments) {
            if (appointment.getAppointmentDateTime().isAfter(now) && appointment.getStatus() != Status.CANCELLED) {
                if (nearestAppointment == null || appointment.getAppointmentDateTime().isBefore(nearestAppointment.getAppointmentDateTime())) {
                    nearestAppointment = appointment;
                }
            }
        }
        if (nearestAppointment != null) {
            LOGGER.info("Nearest appointment for client is at: " + nearestAppointment.getAppointmentDateTime());
        } else {
            LOGGER.info("No appointments");
        }
    }

    public void payRent(Apartment apartment) {
        for (RentalTransaction transaction : rentalTransactions) {
            if (transaction.getApartment().equals(apartment)) {
                transaction.payRent();
            }
        }
    }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

}
