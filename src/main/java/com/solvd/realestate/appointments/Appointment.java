package com.solvd.realestate.appointments;

import com.solvd.realestate.person.Agent;
import com.solvd.realestate.person.Client;
import com.solvd.realestate.transactions.TransactionFee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import com.solvd.realestate.transactions.Bill;

public class Appointment {
    private static final Logger LOGGER = LogManager.getLogger(Appointment.class);
    private LocalDateTime appointmentDateTime;
    private Client client;
    private Agent agent;
    private Purpose purpose;
    private Status status;
    private Bill bill;
    private double duration;

    public Appointment(LocalDateTime appointmentDateTime, Client client, Agent agent, Purpose purpose) {
        this.appointmentDateTime = appointmentDateTime;
        this.client = client;
        this.agent = agent;
        this.purpose = purpose;
        this.bill = new Bill();
        this.bill.calculateBillForMisc(TransactionFee.CONSULTATION);
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

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }
    // Method

    public Consumer<Double> doAppointment = duration -> {
        this.duration = duration;
        this.bill.setAmount(this.purpose.calculateCost(this.bill.getAmount(), duration - purpose.getDefaultDuration()));
        this.status = Status.COMPLETED;
    };

    public Runnable printAppointmentInfo = () -> {
        LOGGER.info("Appointment Information:" +
                "\nDateTime: " + appointmentDateTime +
                "\nClient: " + client.getName() +
                "\nAgent: " + agent.getName() +
                "\nPurpose: " + purpose +
                "\nStatus: " + status +
                "\nBill: " + bill.getAmount() +
                "\nDuration: " + duration + " hours");
    };

}
