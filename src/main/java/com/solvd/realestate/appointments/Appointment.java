package com.solvd.realestate.appointments;

import com.solvd.realestate.person.Agent;
import com.solvd.realestate.person.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Appointment {
    private static final Logger LOGGER = LogManager.getLogger(Appointment.class);

    private LocalDateTime appointmentDateTime;
    private Client client;
    private Agent agent;
    private Purpose purpose;
    private Status status;

    // Map to hold agents and their timetables (list of available times)
    private static Map<Agent, List<LocalDateTime>> agentTimetables;

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

    // Calculate appointment duration based on purpose
    public double calculateAppointmentDuration(double customMultiplier) {
        return purpose.calculateCustomDuration(customMultiplier);
    }

    // Check agent availability using the static agentTimetables map
    public boolean isAgentAvailable() {
        List<LocalDateTime> agentAvailability = agentTimetables.get(agent);
        return agentAvailability != null &&
                agentAvailability.stream().anyMatch(time -> time.isBefore(appointmentDateTime));
    }

    // Book the agent if available, otherwise log unavailability and suggest the next available date
    public void bookAgent() {
        List<LocalDateTime> agentAvailability = agentTimetables.get(agent);

        if (agentAvailability != null && agentAvailability.stream().anyMatch(time -> time.isBefore(appointmentDateTime))) {
            LOGGER.info("Agent booked for appointment on " + appointmentDateTime);
        } else {
            LocalDateTime nextAvailableDate = agentAvailability != null && !agentAvailability.isEmpty()
                    ? agentAvailability.get(0).plusHours(1)
                    : LocalDateTime.now().plusDays(1).withHour(9).withMinute(0);

            LOGGER.info("Agent is not available. Next available date: " + nextAvailableDate);
        }
    }

    // Method to add an agent and their availability to the timetable
    public static void addAgentToTimetable(Agent agent, List<LocalDateTime> availability) {
        agentTimetables.put(agent, availability);
        LOGGER.info("Agent " + agent.getName() + " added to the timetable with availability: " + availability);
    }

    // Print the entire timetable
    public static void printTimetable() {
        LOGGER.info("Agent Timetables:");
        //Lambda with generics
        agentTimetables.forEach((key, value) -> LOGGER.info("Agent: " + key.getName() + ", Availability: " + value));
    }
}
