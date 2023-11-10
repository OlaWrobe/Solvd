package realestate.person;

import realestate.Exceptions.IncorrectAppointmentDateException;
import realestate.appointments.Appointment;
import realestate.appointments.Status;
import realestate.interfaces.AppointmentHandling;
import realestate.interfaces.InformationPrinting;
import realestate.transactions.BuyTransaction;
import realestate.transactions.RentalTransaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Person implements InformationPrinting, AppointmentHandling {
    protected String name;
    protected String surname;
    protected ContactInformation contact;
    protected List<BuyTransaction> buyTransactions;
    protected List<RentalTransaction> rentalTransactions;
    protected List<Appointment> appointments;

    public Person(String name, String surname, ContactInformation contact) {
        this.name = name;
        this.surname = surname;
        this.contact = contact;
        this.buyTransactions = new ArrayList<>();
        this.rentalTransactions = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ContactInformation getContact() {
        return contact;
    }

    public void setContact(ContactInformation contact) {
        this.contact = contact;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void closeAppointment(Appointment appointment) {
        for (Appointment app : this.appointments) {
            if (appointment.equals(app)) {
                app.setStatus(Status.CANCELLED);
            }
        }
    }

    public void makeAppointment(Appointment appointment) throws IncorrectAppointmentDateException {
        if (appointment.getAppointmentDateTime().isAfter(LocalDateTime.now())) {
            appointment.setStatus(Status.REQUESTED);
            this.appointments.add(appointment);
        } else {
            appointment.setStatus(Status.CANCELLED);
            throw new IncorrectAppointmentDateException("Date before today");
        }
    }

    public abstract void nearestAppointmentNotification();
}
