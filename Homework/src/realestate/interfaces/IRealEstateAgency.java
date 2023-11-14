package realestate.interfaces;

import realestate.exceptions.InvalidApartmentIdException;
import realestate.apartment.Apartment;
import realestate.person.Agent;
import realestate.person.Client;

import java.util.List;

public interface IRealEstateAgency {
    public List<Apartment> findSuitableApartments(Client client);

    public Agent findSuitableAgent(Client client);

    public void printAllAgents();

    public void printAllClients();

    public void printAllApartments();

    public void rentApartment(int apartmentId, Client client) throws InvalidApartmentIdException;
}