package com.solvd.realestate;

import com.solvd.realestate.agency.RealEstateAgency;
import com.solvd.realestate.apartment.Apartment;
import com.solvd.realestate.exceptions.DateBeforeTodayException;
import com.solvd.realestate.exceptions.DuplicateDataException;
import com.solvd.realestate.exceptions.InvalidApartmentIdException;
import com.solvd.realestate.exceptions.MissingContactInformationExeption;
import com.solvd.realestate.person.*;
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

        //Creating a real estate agency
        List<Apartment> houseList = new ArrayList<>();
        houseList.add(house1);
        houseList.add(house2);
        houseList.add(house3);
        houseList.add(house4);

        List<Agent> agentList = new ArrayList<>();
        agentList.add(asia);
        agentList.add(basia);

        List<Client> clientList = new ArrayList<>();
        clientList.add(clientOne);
        clientList.add(clientTwo);

        RealEstateAgency myAgency = new RealEstateAgency(houseList, agentList, clientList);
        LOGGER.info("All agency agents: ");
        myAgency.printAllAgents();
        LOGGER.info("All clients: ");
        myAgency.printAllClients();
        LOGGER.info("All available apartments: ");
        myAgency.printAllApartments();

        //Finding suitable apartments
        List<Apartment> suitableApartmentsForClientOne = myAgency.findSuitableApartments(clientOne);
        List<Apartment> suitableApartmentsForClientTwo = myAgency.findSuitableApartments(clientTwo);

        Appointment appointment1 = new Appointment(LocalDateTime.of(2024, 11, 10, 15, 30), clientOne, asia, Purpose.CONSULTATION);
        Appointment appointment2 = new Appointment(LocalDateTime.of(2024, 12, 10, 15, 30), clientTwo, basia, Purpose.RENTAL);

        myAgency.makeAppointment(appointment1);
        clientOne.makeAppointment(appointment2);

        clientOne.nearestAppointmentNotification();

        LOGGER.info("Suitable apartments for customer one");
        for (Apartment ap : suitableApartmentsForClientOne) {
            ap.printInfo();
        }

        LOGGER.info("Suitable apartments for customer two");
        for (Apartment ap : suitableApartmentsForClientTwo) {
            ap.printInfo();
        }
        LOGGER.info("Making transactions: ");
        LOGGER.info("Make a decision as a both customers: ");

        //InvalidApartmentIdException
        try (Scanner scanner = new Scanner(System.in)) {
            LOGGER.info("Which apartment does user one pick: ");
            int apartmentChoice = scanner.nextInt();
            myAgency.rentApartment(apartmentChoice, clientOne);
            LOGGER.info("Which apartment does user two pick: ");
            apartmentChoice = scanner.nextInt();
            myAgency.rentApartment(apartmentChoice, clientTwo);
        } catch (InvalidApartmentIdException e) {
            LOGGER.error("Invalid apartment ID chosen: ");
            return;
        }
        LOGGER.info("Showing that apartments disappear after being bought");
        myAgency.printAllApartments();
        //Maintenance testing
        myAgency.requestMaintenance(clientTwo, house2);
        myAgency.requestMaintenance(clientOne, house1);
        myAgency.printMaintenanceRequests();
        myAgency.doMaintenance();
        myAgency.printMaintenanceRequests();

        myAgency.saveStatus();

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
        }
    }

}