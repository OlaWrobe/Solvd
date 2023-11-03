package realestate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
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
        Agent asia = new Agent("Asia", "Kowalska", asiaContact, asiaLocations, 5000);

        //Adding apartments
        Apartment apartment1 = new Apartment(lublin, 2, 1, false, 2300, 150000);
        Apartment apartment2 = new Apartment(warszawa, 3, 2, true, 3500, 300000);
        Apartment apartment3 = new Apartment(warszawa, 2, 1, false, 3200, 320000);
        Apartment apartment4 = new Apartment(gdansk, 4, 1, false, 5000, 520000);

        //Adding clients
        ClientForm formOne = new ClientForm(3300, 2, 1, warszawa, false, TransactionType.RENTAL);
        ClientForm formTwo = new ClientForm(600000, 4, 1, gdansk, false, TransactionType.BUY);

        ContactInformation clientOneContact = new ContactInformation("+48111111111", "clientone@gmail.com", warszawa, "warszawska", "1");
        ContactInformation clientTwoContact = new ContactInformation("+48222222222", "clienttwo@gmail.com", gdansk, "gdanska", "12/12");

        Client clientOne = new Client("One", "First", clientOneContact, formOne);
        Client clientTwo = new Client("Two", "Second", clientTwoContact, formTwo);

        //Creating a real estate agency
        List<Apartment> apartmentList = new ArrayList<>();
        apartmentList.add(apartment1);
        apartmentList.add(apartment2);
        apartmentList.add(apartment3);
        apartmentList.add(apartment4);

        List<Agent> agentList = new ArrayList<>();
        agentList.add(asia);

        List<Client> clientList = new ArrayList<>();
        clientList.add(clientOne);
        clientList.add(clientTwo);

        RealEstateAgency myAgency = new RealEstateAgency(apartmentList, agentList, clientList);

        System.out.println("All agency agents: ");
        myAgency.printAllAgents();
        System.out.println("\nAll clients: ");
        myAgency.printAllClients();
        System.out.println("All available apartments: ");
        myAgency.printAllApartments();

        //Finding suitable apartments
        List<Apartment> suitableApartmentsForClientOne = myAgency.findSuitableApartments(clientOne);
        List<Apartment> suitableApartmentsForClientTwo = myAgency.findSuitableApartments(clientTwo);

        System.out.println("--------------------------------------------------------");

        System.out.println("Suitable apartments for customer one");
        for (Apartment ap : suitableApartmentsForClientOne) {
            ap.printApartmentInfo();
        }

        System.out.println("Suitable apartments for customer two");
        for (Apartment ap : suitableApartmentsForClientTwo) {
            ap.printApartmentInfo();
        }
        System.out.println("--------------------------------------------------------");
        System.out.println("Making transactions: ");
        myAgency.rentApartment(0, clientOne);
        myAgency.rentApartment(0, clientTwo);
    }
}