package com.solvd.realestate.maintenence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.realestate.agency.RealEstateAgency;
import com.solvd.realestate.apartment.Apartment;
import com.solvd.realestate.person.Client;

public class MaintenanceRequest {
    private final static Logger LOGGER = LogManager.getLogger(RealEstateAgency.class);

    private Client requester;
    private Apartment apartment;
    private MaintenanceType maintenanceType;

    public MaintenanceRequest(Client requester, Apartment apartment, MaintenanceType maintenanceType) {
        this.requester = requester;
        this.apartment = apartment;
        this.maintenanceType = maintenanceType;
    }

    public Client getRequester() {
        return requester;
    }

    public void setRequester(Client requester) {
        this.requester = requester;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    public MaintenanceType getMaintenanceType() {
        return maintenanceType;
    }

    public void setMaintenanceType(MaintenanceType maintenanceType) {
        this.maintenanceType = maintenanceType;
    }

    public void doMaintenance() {
        double hourlyRate = 20.0;
        double cost = maintenanceType.calculateCost(hourlyRate);
        LOGGER.info("Maintenance completed on apartment " + apartment.getApartmentId() +
                ". Total Cost: "+cost +". Maintenance scope "+ maintenanceType.getDescription());
    }
}

