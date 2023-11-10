package realestate.appointments;

import realestate.person.Agent;
import realestate.person.Client;

import java.time.LocalDateTime;

public class Appointment {
    private LocalDateTime appointmentDateTime;
    private Client client;
    private Agent agent;
    private Purpose purpose;
    private Status status;

    public Appointment(LocalDateTime appointmentDateTime, Client client, Agent agent, Purpose purpose) {
        this.appointmentDateTime = appointmentDateTime;
        this.client = client;
        this.agent = agent;
        this.purpose = purpose;
        this.status = null;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
