package com.solvd.realestate.interfaces;

import com.solvd.realestate.apartment.Apartment;
import com.solvd.realestate.person.Agent;
import com.solvd.realestate.person.ClientForm;

@FunctionalInterface
public interface MeetsRequirements {
    boolean meetsRequirements(ClientForm clientForm, Apartment apartment);
}
