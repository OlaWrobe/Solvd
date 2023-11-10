package realestate.interfaces;

import realestate.Exceptions.IncorrectAppointmentDateException;
import realestate.appointments.Appointment;
import realestate.appointments.Purpose;
import realestate.person.Agent;
import realestate.person.Client;

import java.time.LocalDateTime;

public interface AppointmentHandling {
    public void closeAppointment(Appointment appointment);

    public void makeAppointment(Appointment appointment) throws IncorrectAppointmentDateException;

}
