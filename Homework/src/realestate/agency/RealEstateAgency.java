package realestate.agency;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realestate.exceptions.DateBeforeTodayException;
import realestate.exceptions.DuplicateDataException;
import realestate.exceptions.InvalidApartmentIdException;
import realestate.exceptions.InvalidTransactionTypeException;
import realestate.apartment.Apartment;
import realestate.appointments.Appointment;
import realestate.appointments.Status;
import realestate.interfaces.AppointmentHandling;
import realestate.interfaces.IRealEstateAgency;
import realestate.interfaces.RentalActions;
import realestate.person.Agent;
import realestate.person.CityLocation;
import realestate.person.Client;
import realestate.person.ClientForm;
import realestate.transactions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RealEstateAgency implements IRealEstateAgency, AppointmentHandling, RentalActions {
    private final static Logger LOGGER = LogManager.getLogger(RealEstateAgency.class);

    static {
        LOGGER.info("Welcome to XYZ Real estate agency! Here are our services:");
        Bill.printPriceList();
    }

    private Set<CityLocation> locationsOfWork;
    private List<Apartment> apartments;
    private List<Agent> agents;
    private List<Client> clients;
    private List<RentalTransaction> rentalTransactions;
    private List<BuyTransaction> buyTransactions;
    private List<Appointment> appointments;

    public RealEstateAgency(List<Apartment> apartments, List<Agent> agents, List<Client> clients) {
        this.apartments = apartments;
        this.agents = agents;
        this.clients = clients;
        this.rentalTransactions = new ArrayList<>();
        this.buyTransactions = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.locationsOfWork = new HashSet<>();
        for (Apartment apartment : this.apartments) {
            this.locationsOfWork.add(apartment.getLocation());
        }
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<RentalTransaction> getTransactions() {
        return rentalTransactions;
    }

    public void setTransactions(List<RentalTransaction> rentalTransactions) {
        this.rentalTransactions = rentalTransactions;
    }

    public List<BuyTransaction> getBuyTransaction() {
        return this.buyTransactions;
    }

    public void setBuyTransactions(List<BuyTransaction> buyTransactions) {
        this.buyTransactions = buyTransactions;
    }

    public List<Apartment> findSuitableApartments(Client client) {

        ClientForm clientForm = client.getClientForm();
        List<Apartment> finalSuitableApartments = new ArrayList<>();

        for (Apartment app : apartments) {
            if (meetsRequirements(clientForm, app)) {
                double apartmentPrice = clientForm.getTransactionType() == TransactionType.BUY ?
                        app.getBuyingPrice() : clientForm.getTransactionType() == TransactionType.RENTAL ?
                        app.getRentPrice() : 0;

                double budget = clientForm.getBudget();

                if (apartmentPrice != 0) {
                    if (budget >= apartmentPrice) {
                        finalSuitableApartments.add(app);
                    }
                } else {
                    throw new InvalidTransactionTypeException("Incorrect transaction type");
                }
            }
        }
        return finalSuitableApartments;
    }

    private boolean meetsRequirements(ClientForm requirements, Apartment apartments) {
        return requirements.getNeedsParking() == apartments.getHasParking()
                && requirements.getNumberOfBedrooms() <= apartments.getNumberOfBedrooms()
                && requirements.getNumberOfBathrooms() <= apartments.getNumberOfBathrooms()
                && requirements.getCityLocation() == apartments.getLocation();
    }

    public Agent findSuitableAgent(Client client) {
        for (Agent agent : agents) {
            if (client.getContact().getCityLocation() == agent.getCityLocation()) {
                return agent;
            }
        }
        LOGGER.info("No suitable agents");
        return null;
    }

    public void printAllAgents() {
        for (Agent agent : agents) {
            agent.printInfo();
        }
    }

    public void printAllClients() {
        for (Client client : clients) {
            client.printInfo();
        }
    }

    public void printAllApartments() {
        for (Apartment apartment : apartments) {
            apartment.printInfo();
        }
    }

    public void rentApartment(int apartmentId, Client client) throws InvalidApartmentIdException {
        List<Apartment> suitableApartments = this.findSuitableApartments(client);

        Apartment apartmentToBeRentedOrBought = suitableApartments.stream()
                .filter(apartment -> apartment.getApartmentId() == apartmentId)
                .findFirst()
                .orElse(null);

        if (apartmentToBeRentedOrBought != null) {
            if (client.getClientForm().getTransactionType() == TransactionType.RENTAL) {
                RentalTransaction transaction = new RentalTransaction(apartmentToBeRentedOrBought, this.findSuitableAgent(client), client, LocalDate.of(2023, 11, 3), LocalDate.of(2026, 11, 1));
                this.rentalTransactions.add(transaction);
                LOGGER.info(transaction.toString());
                apartments.removeIf(apartment -> apartment.getApartmentId() == apartmentToBeRentedOrBought.getApartmentId());
            } else if (client.getClientForm().getTransactionType() == TransactionType.BUY) {
                BuyTransaction transaction = new BuyTransaction(apartmentToBeRentedOrBought, this.findSuitableAgent(client), client);
                this.buyTransactions.add(transaction);
                LOGGER.info(transaction.toString());
            } else {
                throw new InvalidTransactionTypeException("Incorrect transaction type");
            }
        } else {
            throw new InvalidApartmentIdException("Apartment with index " + apartmentId + " not found.");
        }
    }

    public double getIncome() {
        double total = 0;
        for (Transaction transaction : buyTransactions) {
            total = total + transaction.calculateTransactionFee() + transaction.getBill().getAmount();
        }
        for (Transaction transaction : rentalTransactions) {
            total = total + transaction.calculateTransactionFee() + transaction.getBill().getAmount();
        }
        return total;
    }

    public void closeAppointment(Appointment appointment) {
        for (Appointment app : this.appointments) {
            if (appointment.equals(app)) {
                app.setStatus(Status.CANCELLED);
            }
        }
    }

    public void makeAppointment(Appointment appointment) throws DateBeforeTodayException {
        if (appointment.getAppointmentDateTime().isAfter(LocalDateTime.now())) {
            appointment.setStatus(Status.PLANNED);
            this.appointments.add(appointment);
            for (Client client : clients) {
                if (client.equals(appointment.getClient())) {
                    client.addAppointment(appointment);
                }
            }
        } else {
            appointment.setStatus(Status.CANCELLED);
            throw new DateBeforeTodayException("Date before today");
        }
    }

    public void payRent(Apartment apartment) {
        for (RentalTransaction transaction : rentalTransactions) {
            if (transaction.getApartment().equals(apartment)) {
                transaction.payRent();
            }
        }
    }

    public void acceptAppointment(Client client, LocalDateTime date) {
        for (Appointment app : client.getAppointments()) {
            if (app.getAppointmentDateTime() == date) {
                app.setStatus(Status.PLANNED);
                appointments.add(app);
                break;
            }
        }
    }

    public void addApartment(Apartment apartment) throws DuplicateDataException {
        checkForDuplicate(apartments, apartment);
        apartments.add(apartment);
    }

    public void addAgent(Agent agent) throws DuplicateDataException {
        checkForDuplicate(agents, agent);
        agents.add(agent);
    }

    public void addClient(Client client) throws DuplicateDataException {
        checkForDuplicate(clients, client);
        clients.add(client);
    }

    public void addRentalTransaction(RentalTransaction rentalTransaction) throws DuplicateDataException {
        checkForDuplicate(rentalTransactions, rentalTransaction);
        rentalTransactions.add(rentalTransaction);
    }

    public void addBuyTransaction(BuyTransaction buyTransaction) throws DuplicateDataException {
        checkForDuplicate(buyTransactions, buyTransaction);
        buyTransactions.add(buyTransaction);
    }

    public void addAppointment(Appointment appointment) throws DuplicateDataException {
        checkForDuplicate(appointments, appointment);
        appointments.add(appointment);
    }

    private <T> void checkForDuplicate(List<T> list, T newItem) throws DuplicateDataException {
        if (list.contains(newItem)) {
            throw new DuplicateDataException("Duplicate data found: " + newItem);
        }
    }
}
