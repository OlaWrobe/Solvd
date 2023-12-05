package com.solvd.realestate.interfaces;

import com.solvd.realestate.apartment.Apartment;
import com.solvd.realestate.exceptions.InvalidApartmentIdException;
import com.solvd.realestate.person.Client;
import com.solvd.realestate.person.ClientForm;
import com.solvd.realestate.transactions.Transaction;

import java.util.List;
import java.util.function.BiPredicate;

public interface IRealEstateAgency {
    public List<Apartment> findSuitableApartments(Client client, BiPredicate<ClientForm, Apartment> meetsRequirements);

    public void printAllAgents();

    public void printAllClients();

    public void printAllApartments();

    public void rentApartment(int apartmentId, Client client, List<Apartment> suitableApartments, TransactionHandler<Transaction> handler) throws InvalidApartmentIdException;
}