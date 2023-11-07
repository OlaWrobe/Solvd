package realestate.agency;

public final class AgencyStatus {
    public final int customerCount;
    public final int agentCount;
    public final int propertyCount;
    public final double allTransactionIncome;

    AgencyStatus(RealEstateAgency realEstateAgency) {
        this.customerCount = realEstateAgency.getClients().size();
        this.agentCount = realEstateAgency.getAgents().size();
        this.propertyCount = realEstateAgency.getApartments().size();
        allTransactionIncome = realEstateAgency.getIncome();
    }

    public int getCustomerCount() {
        return this.customerCount;
    }

    public int getAgentCount() {
        return agentCount;
    }

    public int getPropertyCount() {
        return propertyCount;
    }

    public double getAllTransactionIncome() {
        return allTransactionIncome;
    }
}
