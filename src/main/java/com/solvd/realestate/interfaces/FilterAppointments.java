package com.solvd.realestate.interfaces;

import com.solvd.realestate.appointments.Appointment;

@FunctionalInterface
public interface FilterAppointments {
    boolean appointmentFilter(Appointment appointment);
}
