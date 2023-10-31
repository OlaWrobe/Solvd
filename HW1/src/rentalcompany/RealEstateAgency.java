package rentalcompany;

import java.util.ArrayList;
import java.util.List;

public class RealEstateAgency {
    private List<Apartment> apartments;
    private List<Agent> agents;
    private List<Client> clients;
    private List<RentalTransaction> rentalTransactions;
    private List<Bill> bills;

    public RealEstateAgency(List<Apartment> apartments, List<Agent> agents, List<Client> clients, List<RentalTransaction> rentalTransactions, List<Bill> bills) {
        this.apartments = apartments;
        this.agents = agents;
        this.clients = clients;
        this.rentalTransactions = rentalTransactions;
        this.bills = bills;
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
    public List<Bill> getServices(){
    return this.bills;
    }
    public void setServices(List<Bill> bills){
        this.bills = bills;
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

}
