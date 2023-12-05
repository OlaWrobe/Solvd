package com.solvd.realestate;

import com.solvd.realestate.agency.RealEstateAgency;
import com.solvd.realestate.apartment.Apartment;
import com.solvd.realestate.appointments.Status;
import com.solvd.realestate.exceptions.DateBeforeTodayException;
import com.solvd.realestate.exceptions.DuplicateDataException;
import com.solvd.realestate.exceptions.InvalidApartmentIdException;
import com.solvd.realestate.exceptions.MissingContactInformationExeption;
import com.solvd.realestate.maintenence.MaintenanceType;
import com.solvd.realestate.person.*;
import com.solvd.realestate.transactions.BuyTransaction;
import com.solvd.realestate.transactions.RentalTransaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.realestate.appointments.Appointment;
import com.solvd.realestate.appointments.Purpose;
import com.solvd.realestate.transactions.TransactionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static {
        System.setProperty("log4j2.configurationFile", "src/main/resources/log4j2.xml");
    }

    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws DateBeforeTodayException, DuplicateDataException, InvalidApartmentIdException {

        //Locations
        CityLocation warszawa = new CityLocation("Warszawa", "Mazowieckie", "12-123");
        CityLocation gdansk = new CityLocation("Gdansk", "Pomorskie", "12-123");
        CityLocation lublin = new CityLocation("Lublin", "Lubelskie", "12-123");

        //Adding an agent
        List<CityLocation> asiaLocations = new ArrayList<>();
        asiaLocations.add(warszawa);
        asiaLocations.add(gdansk);
        ContactInformation asiaContact = new ContactInformation("+48123456789", "123@gmail.com", warszawa, "Mokotowa", "12/22");
        ContactInformation basiaContact = new ContactInformation("+48123456789", "123@gmail.com", gdansk, "Mokotowa", "12/22");
        Agent asia = new Agent("Asia", "Kowalska", asiaContact, warszawa, 5000);
        Agent basia = new Agent("Basia", "Kowalska", basiaContact, gdansk, 5100);

        //Adding apartments
        Apartment house1 = new Apartment(lublin, 2, 1, true, 2300, 150000);
        Apartment house2 = new Apartment(warszawa, 2, 1, false, 3200, 300000);
        Apartment house3 = new Apartment(gdansk, 5, 1, false, 3200, 320000);
        Apartment house4 = new Apartment(gdansk, 4, 1, false, 5000, 520000);

        //Adding clients
        ClientForm formOne = new ClientForm(3300, 2, 1, warszawa, false, TransactionType.RENTAL);
        ClientForm formTwo = new ClientForm(600000, 4, 1, gdansk, false, TransactionType.BUY);

        ContactInformation clientOneContact = new ContactInformation("+48111111111", "clientone@gmail.com", warszawa, "warszawska", "1");
        ContactInformation clientTwoContact = new ContactInformation("+48222222222", "clienttwo@gmail.com", gdansk, "gdanska", "12/12");

        Client clientOne = new Client("One", "First", clientOneContact, formOne);
        Client clientTwo = new Client("Two", "Second", clientTwoContact, formTwo);

        RealEstateAgency myAgency = new RealEstateAgency();
        //Adding Elements
        myAgency.addAgent(asia);
        myAgency.addAgent(basia);

        myAgency.addClient(clientOne);
        myAgency.addClient(clientTwo);

        myAgency.addApartment(house1);
        myAgency.addApartment(house2);
        myAgency.addApartment(house3);
        myAgency.addApartment(house4);

        //Printing all agents and clients and apartments
        LOGGER.info("All agency agents: ");
        myAgency.printAllAgents();
        LOGGER.info("All clients: ");
        myAgency.printAllClients();
        LOGGER.info("All available apartments: ");
        myAgency.printAllApartments();

        //Transactions Buy and Rent
        //InvalidApartmentIdException
        try (Scanner scanner = new Scanner(System.in)) {
            LOGGER.info("Suitable apartments for customer one");
            List<Apartment> suitableApartmentsForClientOne = myAgency.findSuitableApartments(clientOne, (clientForm, app) ->
                    clientForm.getNeedsParking() == app.getHasParking()
                            && clientForm.getNumberOfBedrooms() <= app.getNumberOfBedrooms()
                            && clientForm.getNumberOfBathrooms() <= app.getNumberOfBathrooms()
                            && clientForm.getCityLocation() == app.getLocation());
            for (Apartment ap : suitableApartmentsForClientOne) {
                ap.printInfo();
            }
            LOGGER.info("Which apartment does user one pick: ");
            int apartmentChoice = scanner.nextInt();
            myAgency.rentApartment(apartmentChoice, clientOne, suitableApartmentsForClientOne, (transaction -> {
                myAgency.addRentTransaction((RentalTransaction) transaction);
            }));

            List<Apartment> suitableApartmentsForClientTwo = myAgency.findSuitableApartments(clientTwo, (clientForm, app) ->
                    clientForm.getNeedsParking() == app.getHasParking()
                            && clientForm.getNumberOfBedrooms() <= app.getNumberOfBedrooms()
                            && clientForm.getNumberOfBathrooms() <= app.getNumberOfBathrooms()
                            && (clientForm.getCityLocation() == app.getLocation() || clientForm.getCityLocation().equals(lublin)));

            LOGGER.info("Suitable apartments for customer two");
            for (Apartment ap : suitableApartmentsForClientTwo) {
                ap.printInfo();
            }
            LOGGER.info("Which apartment does user two pick: ");
            apartmentChoice = scanner.nextInt();
            myAgency.rentApartment(apartmentChoice, clientTwo, suitableApartmentsForClientTwo, (transaction -> {
                myAgency.addBuyTransaction((BuyTransaction) transaction);
            }));
        } catch (InvalidApartmentIdException e) {
            LOGGER.error("Invalid apartment ID chosen: ");
            return;
        }
        LOGGER.info("Showing that apartments disappear after being bought");
        //All apartments withought the bought one
        myAgency.printAllApartments();
        //Paying rent


        LOGGER.info("-----------------------------------------------------------");
        //Maintenance testing
        myAgency.requestMaintenance(clientTwo, house2, MaintenanceType.ELECTRICAL);
        myAgency.requestMaintenance(clientOne, house1, MaintenanceType.GENERAL);
        myAgency.printMaintenanceRequests();
        myAgency.doMaintenance();
        myAgency.printMaintenanceRequests();

        LOGGER.info("-----------------------------------------------------------");
        //Appointments
        Appointment appointment1 = new Appointment(LocalDateTime.of(2024, 11, 10, 15, 30), clientOne, asia, Purpose.CONSULTATION);
        Appointment appointment2 = new Appointment(LocalDateTime.of(2024, 12, 13, 15, 30), clientOne, asia, Purpose.RENTAL);
        Appointment appointment3 = new Appointment(LocalDateTime.of(2024, 9, 10, 15, 30), clientOne, asia, Purpose.ISSUE);

        clientOne.makeAppointment(appointment1, (appointment, date) -> appointment.getAppointmentDateTime().isAfter(LocalDateTime.now()));
        clientOne.makeAppointment(appointment2, (appointment, date) -> appointment.getAppointmentDateTime().isAfter(LocalDateTime.now()));
        myAgency.makeAppointment(appointment3, (appointment, date) -> appointment.getAppointmentDateTime().isAfter(LocalDateTime.now().plusDays(1)));

        for (Appointment appointment : clientOne.getAppointments()
        ) {
            LOGGER.info("Date of app: " + appointment.getAppointmentDateTime() + " Status " + appointment.getStatus());
        }
        myAgency.acceptAppointment(clientOne, LocalDateTime.of(2024, 9, 10, 15, 30));
        clientOne.closeAppointment(appointment2);
        myAgency.closeAppointment(appointment1);
        LOGGER.info("Updated appointments: ");
        for (Appointment appointment : clientOne.getAppointments()
        ) {
            appointment.getStatus().printAppointmentStatus();
        }
        clientOne.nearestAppointmentNotification((appointment, date) -> {
            return appointment.getAppointmentDateTime().isAfter(date) && appointment.getStatus() != Status.CANCELLED;
        });
        clientOne.doAppointment(appointment3, 3);
        appointment3.printAppointmentInfo.run();
        //Saving agency status
        myAgency.saveStatus();

        /* TESTING EXCEPTIONS
        //duplicate date exception
        myAgency.addApartment(house1);

        //DateBeforeTodayException
        LocalDateTime appointmentDateTime = LocalDateTime.of(2021, 11, 10, 15, 30);
        clientOne.makeAppointment(new Appointment(appointmentDateTime, clientOne, asia, Purpose.CONSULTATION));

        //MissingContactInformation
        try {
            ContactInformation contactInformationWithMissingData = new ContactInformation(null, null, null, null, null);
        } catch (MissingContactInformationExeption e) {
            LOGGER.error(e);
        }*/
    }
}