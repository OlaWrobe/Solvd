package realestate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realestate.Exceptions.IncorrectAppointmentDateException;
import realestate.Exceptions.InvalidApartmentIdException;
import realestate.Exceptions.MissingContactInformationExeption;
import realestate.agency.AgencyStatus;
import realestate.agency.RealEstateAgency;
import realestate.apartment.Apartment;
import realestate.appointments.Appointment;
import realestate.appointments.Purpose;
import realestate.person.*;
import realestate.transactions.TransactionType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static {
        System.setProperty("log4j2.configurationFile", "resources/log4j2.xml");
    }

    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

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

        LOGGER.info("Suitable apartments for customer one");
        for (Apartment ap : suitableApartmentsForClientOne) {
            ap.printInfo();
        }

        LOGGER.info("Suitable apartments for customer two");
        for (Apartment ap : suitableApartmentsForClientTwo) {
            ap.printInfo();
        }
        Scanner scanner = new Scanner(System.in);
        LOGGER.info("Make a decision as a customer two, which one will you be buying? Enter apartment number you see on screen ");
        int apartmentChoice = scanner.nextInt();

        LOGGER.info("Making transactions: ");

        try {
            myAgency.rentApartment(2, clientOne);
        } catch (InvalidApartmentIdException e) {
            LOGGER.error("Invalid apartment ID");
        }
        try {
            myAgency.rentApartment(apartmentChoice, clientTwo);
        } catch (InvalidApartmentIdException e) {
            LOGGER.error("Invalid apartment ID chosen: " + apartmentChoice);
        }

        AgencyStatus agencyStatus = new AgencyStatus(myAgency);
        agencyStatus.printInfo();

        try {
            LocalDateTime appointmentDateTime = LocalDateTime.of(2021, 11, 10, 15, 30);
            clientOne.makeAppointment(new Appointment(appointmentDateTime, clientOne, asia, Purpose.CONSULTATION));
        } catch (IncorrectAppointmentDateException e) {
            LOGGER.error(e);
        }
    }
}