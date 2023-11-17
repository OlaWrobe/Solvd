package com.solvd.realestate.interfaces;

import com.solvd.realestate.appointments.Appointment;
import com.solvd.realestate.exceptions.DateBeforeTodayException;

public interface AppointmentHandling {
    public void closeAppointment(Appointment appointment);

    public void makeAppointment(Appointment appointment) throws DateBeforeTodayException;

}
