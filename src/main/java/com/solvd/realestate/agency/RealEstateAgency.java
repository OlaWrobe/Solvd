package com.solvd.realestate.agency;

import com.solvd.realestate.apartment.Apartment;
import com.solvd.realestate.appointments.Status;
import com.solvd.realestate.custom.CustomLinkedList;
import com.solvd.realestate.exceptions.DateBeforeTodayException;
import com.solvd.realestate.exceptions.DuplicateDataException;
import com.solvd.realestate.exceptions.InvalidApartmentIdException;
import com.solvd.realestate.exceptions.InvalidTransactionTypeException;
import com.solvd.realestate.interfaces.*;
import com.solvd.realestate.maintenence.MaintenanceType;
import com.solvd.realestate.person.Agent;
import com.solvd.realestate.person.CityLocation;
import com.solvd.realestate.person.ClientForm;
import com.solvd.realestate.transactions.*;
import com.solvd.realestate.maintenence.MaintenanceRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.realestate.appointments.Appointment;
import com.solvd.realestate.person.Client;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;


public class RealEstateAgency implements IRealEstateAgency, AppointmentHandling, RentalActions {
    private final static Logger LOGGER = LogManager.getLogger(RealEstateAgency.class);

    static {
        LOGGER.info("Welcome to XYZ Real estate agency! Here are our services:");
        Bill.printPriceList();
    }

    private static CustomLinkedList<AgencyStatus> allLogs = new CustomLinkedList<>();
    private Set<CityLocation> locationsOfWork;
    private List<Apartment> apartments;
    private List<Agent> agents;
    private List<Client> clients;
    private List<RentalTransaction> rentalTransactions;
    private List<BuyTransaction> buyTransactions;
    private Queue<MaintenanceRequest> maintenanceRequests;

    //Constructor
    public RealEstateAgency() {
        this.apartments = new ArrayList<>();
        this.agents = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.rentalTransactions = new ArrayList<>();
        this.buyTransactions = new ArrayList<>();
        this.locationsOfWork = new HashSet<>();
        this.maintenanceRequests = new LinkedList<>();
    }

    //getters and setters
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

    //Methods
    public List<Apartment> findSuitableApartments(Client client, BiPredicate<ClientForm, Apartment> meetsRequirements) {
        ClientForm clientForm = client.getClientForm();
        List<Apartment> finalSuitableApartments = new ArrayList<>();

        for (Apartment app : apartments) {
            if (meetsRequirements.test(clientForm, app)) {
                double apartmentPrice = switch (clientForm.getTransactionType()) {
                    case BUY -> app.getBuyingPrice();
                    case RENTAL -> app.getRentPrice();
                };
                double budget = clientForm.getBudget();

                if (apartmentPrice != 0) {
                    if (budget >= apartmentPrice) {
                        finalSuitableApartments.add(app);
                    } else {
                        throw new InvalidTransactionTypeException("Incorrect transaction type");
                    }
                }
            }
        }
        return finalSuitableApartments;
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

    public void printMaintenanceRequests() {
        LOGGER.info("Maintenance Requests:");

        for (MaintenanceRequest request : this.maintenanceRequests) {
            LOGGER.info("For " + request.getRequester().getName() + " in apartment number " + request.getApartment().getApartmentId());
        }
    }

    Function<Client, Agent> findSuitableAgent = c -> {
        for (Agent agent : agents) {
            if (c.getContact().getCityLocation().equals(agent.getCityLocation())) {
                return agent;
            }
        }
        LOGGER.info("No suitable agents");
        return null;
    };

    public void rentApartment(int apartmentId, Client client, List<Apartment> suitableAp, TransactionHandler<Transaction> handler) throws InvalidApartmentIdException {
        List<Apartment> suitableApartments = suitableAp;

        Apartment apartmentToBeRentedOrBought = suitableApartments.stream()
                .filter(apartment -> apartment.getApartmentId() == apartmentId)
                .findFirst()
                .orElse(null);
        if (apartmentToBeRentedOrBought != null) {
            TransactionType transactionType = client.getClientForm().getTransactionType();

            if (transactionType == TransactionType.RENTAL) {
                RentalTransaction transaction = new RentalTransaction(apartmentToBeRentedOrBought, this.findSuitableAgent.apply(client), client, LocalDate.of(2023, 11, 3), LocalDate.of(2026, 11, 1));
                handler.handleTransaction(transaction);
                LOGGER.info(transaction.toString());
            } else if (transactionType == TransactionType.BUY) {
                BuyTransaction transaction = new BuyTransaction(apartmentToBeRentedOrBought, this.findSuitableAgent.apply(client), client);
                handler.handleTransaction(transaction);
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
        for (BuyTransaction transaction : buyTransactions) {
            total = total + transaction.getBill().getAmount();
        }
        for (Transaction transaction : rentalTransactions) {
            total = total + transaction.getBill().getAmount();
        }
        return total;
    }

    public void closeAppointment(Appointment appointment) {
        for (Client client : clients) {
            for (Appointment app : client.getAppointments()) {
                if (appointment.equals(app)) {
                    app.setStatus(Status.CANCELLED);
                }
            }
        }
    }

    public void makeAppointment(Appointment appointment, FilterAppointments<LocalDateTime> filter) throws DateBeforeTodayException {
        if (filter.appointmentFilter(appointment, LocalDateTime.now())) {
            appointment.setStatus(Status.CONFIRMED);
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

    @Override
    public void doAppointment(Appointment appointment, double duration) {
        for (Client client : clients
        ) {
            for (Appointment app : client.getAppointments()
            ) {
                if (app.equals(appointment)) {
                    app.doAppointment.accept(duration);
                    return;
                }

            }
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
                app.setStatus(Status.CONFIRMED);
                break;
            }
        }
    }

    public void addApartment(Apartment apartment) throws DuplicateDataException {
        checkForDuplicate(apartments, apartment);
        apartments.add(apartment);
        this.locationsOfWork.add(apartment.getLocation());
    }

    public void addAgent(Agent agent) throws DuplicateDataException {
        checkForDuplicate(agents, agent);
        agents.add(agent);
    }

    public void addClient(Client client) throws DuplicateDataException {
        checkForDuplicate(clients, client);
        clients.add(client);
    }

    public void addBuyTransaction(BuyTransaction transaction) {
        this.buyTransactions.add(transaction);
        apartments.removeIf(apartment -> apartment.getApartmentId() == transaction.getApartment().getApartmentId());
    }

    public void addRentTransaction(RentalTransaction transaction) {
        this.rentalTransactions.add(transaction);
        apartments.removeIf(apartment -> apartment.getApartmentId() == transaction.getApartment().getApartmentId());
    }

    private <T> void checkForDuplicate(List<T> list, T newItem) throws DuplicateDataException {
        if (list.contains(newItem)) {
            throw new DuplicateDataException("Duplicate data found: " + newItem);
        }
    }

    public void saveStatus() {
        AgencyStatus newStatus = new AgencyStatus(this);
        this.allLogs.add(newStatus);
        LOGGER.info(this.allLogs.toString());
    }

    public void requestMaintenance(Client requester, Apartment apartment, MaintenanceType maintenanceType) {
        MaintenanceRequest maintenanceRequest = new MaintenanceRequest(requester, apartment, maintenanceType);
        maintenanceRequests.add(maintenanceRequest);
    }

    public void doMaintenance() {
        MaintenanceRequest request = this.maintenanceRequests.remove();
        request.doMaintenance();
    }

}
