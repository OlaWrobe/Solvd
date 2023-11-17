package realestate.maintenence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realestate.agency.RealEstateAgency;
import realestate.apartment.Apartment;
import realestate.person.Client;

public class MaintenanceRequest {
    private final static Logger LOGGER = LogManager.getLogger(RealEstateAgency.class);

    private Client requester;
    private Apartment apartment;

    public MaintenanceRequest(Client requester, Apartment apartment) {
        this.requester = requester;
        this.apartment = apartment;
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

    public void doMaintenence() {
        LOGGER.info("Maintenence done on apartment " + this.apartment);
    }
}
