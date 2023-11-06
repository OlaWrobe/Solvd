package realestate;

import realestate.apartment.House;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RealEstateAgency {
    private List<House> houses;
    private List<Agent> agents;
    private List<Client> clients;
    private List<RentalTransaction> rentalTransactions;
    private List<BuyTransaction> buyTransactions;

    public RealEstateAgency(List<House> houses, List<Agent> agents, List<Client> clients) {
        this.houses = houses;
        this.agents = agents;
        this.clients = clients;
        this.rentalTransactions = new ArrayList<>();
        this.buyTransactions = new ArrayList<>();
    }

    public List<House> getApartments() {
        return houses;
    }

    public void setApartments(List<House> houses) {
        this.houses = houses;
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

    public List<House> findSuitableApartments(Client client) {

        ClientForm clientForm = client.getClientForm();
        List<House> finalSuitableHouses = new ArrayList<>();

        for (House app : houses) {
            if (meetsRequirements(clientForm, app)) {
                double apartmentPrice = clientForm.getTransactionType() == TransactionType.BUY ?
                        app.getBuyingPrice() : clientForm.getTransactionType() == TransactionType.RENTAL ?
                        app.getRentPrice() : 0;

                double budget = clientForm.getBudget();

                if (apartmentPrice != 0) {
                    if (budget >= apartmentPrice) {
                        finalSuitableHouses.add(app);
                    }
                } else {
                    System.out.println("Incorrect transaction type");
                }
            }
        }
        return finalSuitableHouses;
    }

    private boolean meetsRequirements(ClientForm requirements, House house) {
        return requirements.getNeedsParking() == house.getHasParking()
                && requirements.getNumberOfBedrooms() <= house.getNumberOfBedrooms()
                && requirements.getNumberOfBathrooms() <= house.getNumberOfBathrooms()
                && requirements.getLocation() == house.getLocation();
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
            agent.printInfo();
        }
    }

    public void printAllClients() {
        for (Client client : clients) {
            client.printInfo();
        }
    }

    public void printAllApartments() {
        for (House house : houses) {
            house.printApartmentInfo();
        }
    }

    public void rentApartment(int apartmentId, Client client) {
        List<House> suitableHouses = this.findSuitableApartments(client);
        if (this.findSuitableAgent(client) == null || suitableHouses.isEmpty()) {
            return;
        } else {
            House houseToBeRentedOrBought = suitableHouses.get(apartmentId);
            if (client.getClientForm().getTransactionType() == TransactionType.RENTAL) {
                RentalTransaction transaction = new RentalTransaction(houseToBeRentedOrBought, this.findSuitableAgent(client), client, LocalDate.of(2023, 11, 3), LocalDate.of(2026, 11, 1));
                this.rentalTransactions.add(transaction);
                transaction.printTransaction();
            } else if (client.getClientForm().getTransactionType() == TransactionType.BUY) {
                BuyTransaction transaction = new BuyTransaction(houseToBeRentedOrBought, this.findSuitableAgent(client), client);
                this.buyTransactions.add(transaction);
                transaction.printTransaction();
            } else {
                System.out.println("invalid transaction");
            }
        }
    }
}
