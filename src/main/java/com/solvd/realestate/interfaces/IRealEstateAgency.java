package com.solvd.realestate.interfaces;

import com.solvd.realestate.apartment.Apartment;
import com.solvd.realestate.exceptions.InvalidApartmentIdException;
import com.solvd.realestate.person.Agent;
import com.solvd.realestate.person.Client;

import java.util.List;

public interface IRealEstateAgency {
    public List<Apartment> findSuitableApartments(Client client);


    public void printAllAgents();

    public void printAllClients();

    public void printAllApartments();

    public void rentApartment(int apartmentId, Client client) throws InvalidApartmentIdException;
}