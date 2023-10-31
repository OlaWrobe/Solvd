package realestate;

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
    public List<BuyTransaction> getBuyTransaction(){
    return this.buyTransactions;
    }
    public void setBuyTransactions(List<BuyTransaction> buyTransactions){
        this.buyTransactions = buyTransactions;
    }

    public List<Apartment> findSuitableApartments(Client client)
    {
        ClientForm requirements = client.getClientForm();
        List<Apartment> apartmentList = new ArrayList<>();
        if(requirements.getTransactionType().equals(TransactionType.BUY))
        {
        for (Apartment apartment : apartments) {
            if(requirements.getBudget() >= apartment.getRentPrice()&& requirements.getNeedsParking() == apartment.getHasParking() && requirements.getNumberOfBedrooms() == apartment.getNumberOfBedrooms() && requirements.getNumberOfBathrooms() == apartment.getNumberOfBathrooms() && requirements.getLocation() == apartment.getLocation()){
                apartmentList.add(apartment);}
        }
        } else if (requirements.getTransactionType().equals(TransactionType.RENTAL)) {
            for (Apartment apartment : apartments) {
                if(requirements.getBudget() >= apartment.getRentPrice()&& requirements.getNeedsParking() == apartment.getHasParking() && requirements.getNumberOfBedrooms() == apartment.getNumberOfBedrooms() && requirements.getNumberOfBathrooms() == apartment.getNumberOfBathrooms() && requirements.getLocation() == apartment.getLocation()){
                    apartmentList.add(apartment);}
            }
        } else {
            System.out.println("Error. Incorrect transaction type");
        }
        return apartmentList;
    }

    public Agent findSuitableAgent(Client client)
    {
        for(Agent agent : agents)
        {
            for(CityLocation area : agent.getAreasOfWork())
            {
                if(client.getContact().getCityLocation() == area)
                {
                    return agent;
                }
            }
        }
        System.out.println("No suitable agents");
        return null;
    }
    public void addRentalTransaction(RentalTransaction rentalTransaction)
    {
        this.rentalTransactions.add(rentalTransaction);
    }
    public void addBuyTransaction(BuyTransaction buyTransaction)
    {
        this.buyTransactions.add(buyTransaction);
    }

}
