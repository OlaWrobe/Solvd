package realestate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RealEstateAgency {
    private List<Apartment> apartments;
    private List<Agent> agents;
    private List<Client> clients;
    private List<RentalTransaction> rentalTransactions;
    private List<BuyTransaction> buyTransactions;

    public RealEstateAgency(List<Apartment> apartments, List<Agent> agents, List<Client> clients) {
        this.apartments = apartments;
        this.agents = agents;
        this.clients = clients;
        this.rentalTransactions = new ArrayList<>();
        this.buyTransactions = new ArrayList<>();
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
                    System.out.println("Incorrect transaction type");
                }
            }
        }
        return finalSuitableApartments;
    }

    private boolean meetsRequirements(ClientForm requirements, Apartment apartment) {
        return requirements.getNeedsParking() == apartment.getHasParking()
                && requirements.getNumberOfBedrooms() <= apartment.getNumberOfBedrooms()
                && requirements.getNumberOfBathrooms() <= apartment.getNumberOfBathrooms()
                && requirements.getLocation() == apartment.getLocation();
    }

    public Agent findSuitableAgent(Client client) {
        for (Agent agent : agents) {
            for (CityLocation area : agent.getAreasOfWork()) {
                if (client.getContact().getCityLocation() == area) {
                    return agent;
                }
            }
        }
        System.out.println("No suitable agents");
        return null;
    }

    public void printAllAgents() {
        for (Agent agent : agents) {
            agent.printAgentInfo();
        }
    }

    public void printAllClients() {
        for (Client client : clients) {
            client.printClientInfo();
        }
    }

    public void printAllApartments() {
        for (Apartment apartment : apartments) {
            apartment.printApartmentInfo();
        }
    }

    public void rentApartment(int apartmentId, Client client) {
        List<Apartment> suitableApartments = this.findSuitableApartments(client);
        if (this.findSuitableAgent(client) == null || suitableApartments.isEmpty()) {
            return;
        } else {
            Apartment apartmentToBeRented = suitableApartments.get(apartmentId);
            if (client.getClientForm().getTransactionType() == TransactionType.RENTAL) {
                RentalTransaction transaction = new RentalTransaction(apartmentToBeRented, this.findSuitableAgent(client), client, LocalDate.of(2023, 11, 3), LocalDate.of(2026, 11, 1));
                this.rentalTransactions.add(transaction);
                transaction.printTransaction();
            } else if (client.getClientForm().getTransactionType() == TransactionType.BUY) {
                BuyTransaction transaction = new BuyTransaction(apartmentToBeRented, this.findSuitableAgent(client), client);
                this.buyTransactions.add(transaction);
                transaction.printTransaction();
            } else {
                System.out.println("invalid transaction");
            }
        }
    }
}
