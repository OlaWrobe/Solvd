package realestate.interfaces;

import realestate.apartment.Apartment;
import realestate.person.Agent;
import realestate.person.Client;

import java.util.List;

public interface IRealEstateAgency {
    List<Apartment> findSuitableApartments(Client client);

    Agent findSuitableAgent(Client client);

    void printAllAgents();

    void printAllClients();

    void printAllApartments();

    void rentApartment(int apartmentId, Client client);
}