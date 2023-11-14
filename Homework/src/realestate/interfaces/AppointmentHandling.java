package realestate.interfaces;

import realestate.exceptions.DateBeforeTodayException;
import realestate.appointments.Appointment;

public interface AppointmentHandling {
    public void closeAppointment(Appointment appointment);

    public void makeAppointment(Appointment appointment) throws DateBeforeTodayException;

}
