package realestate.interfaces;

import realestate.appointments.Appointment;
import realestate.exceptions.DateBeforeTodayException;

public interface AppointmentHandling {
    public void closeAppointment(Appointment appointment);

    public void makeAppointment(Appointment appointment) throws DateBeforeTodayException;

}
