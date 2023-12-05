package com.solvd.realestate.interfaces;

import com.solvd.realestate.appointments.Appointment;

@FunctionalInterface
public interface FilterAppointments<T> {
    boolean appointmentFilter(Appointment appointment, T condition);
}
