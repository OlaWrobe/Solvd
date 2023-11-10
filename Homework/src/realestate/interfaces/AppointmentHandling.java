package realestate.interfaces;

import realestate.Exceptions.IncorrectAppointmentDateException;
import realestate.appointments.Appointment;

public interface AppointmentHandling {
    public void closeAppointment(Appointment appointment);

    public void makeAppointment(Appointment appointment) throws IncorrectAppointmentDateException;

}
