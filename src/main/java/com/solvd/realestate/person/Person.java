package com.solvd.realestate.person;

import com.solvd.realestate.appointments.Status;
import com.solvd.realestate.exceptions.DateBeforeTodayException;
import com.solvd.realestate.interfaces.AppointmentHandling;
import com.solvd.realestate.interfaces.FilterAppointments;
import com.solvd.realestate.interfaces.InformationPrinting;
import com.solvd.realestate.appointments.Appointment;
import com.solvd.realestate.transactions.BuyTransaction;
import com.solvd.realestate.transactions.RentalTransaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Person implements InformationPrinting, AppointmentHandling {
    protected String name;
    protected String surname;
    protected ContactInformation contact;
    protected List<Appointment> appointments;

    public Person(String name, String surname, ContactInformation contact) {
        this.name = name;
        this.surname = surname;
        this.contact = contact;
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

    public void makeAppointment(Appointment appointment, FilterAppointments<LocalDateTime> filter) throws DateBeforeTodayException {
        if (filter.appointmentFilter(appointment, LocalDateTime.now())) {
            appointment.setStatus(Status.PLANNED);
            this.appointments.add(appointment);
        } else {
            appointment.setStatus(Status.CANCELLED);
            throw new DateBeforeTodayException("Date before today");
        }
    }

    @Override
    public void doAppointment(Appointment appointment, double duration) {
        for (Appointment app : appointments
        ) {
            if (app.equals(appointment)) {
                app.doAppointment.accept(duration);
                return;
            }
        }
    }

    public abstract void nearestAppointmentNotification(FilterAppointments<LocalDateTime> filter);
}
