package com.solvd.realestate.interfaces;

import com.solvd.realestate.appointments.Appointment;
import com.solvd.realestate.exceptions.DateBeforeTodayException;

import java.time.LocalDateTime;

public interface AppointmentHandling {
    public void closeAppointment(Appointment appointment);

    public void makeAppointment(Appointment appointment, FilterAppointments<LocalDateTime> date) throws DateBeforeTodayException;

    public void doAppointment(Appointment appointment, double duration);
}
