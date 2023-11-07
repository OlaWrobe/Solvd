package realestate.agency;

import realestate.interfaces.InformationPrinting;

import java.time.LocalDateTime;

public final class AgencyStatus implements InformationPrinting {
    private final LocalDateTime logTime;
    private final int customerCount;
    private final int agentCount;
    private final int propertyCount;
    private final double allTransactionIncome;

    public AgencyStatus(RealEstateAgency realEstateAgency) {
        this.logTime = LocalDateTime.now();
        this.customerCount = realEstateAgency.getClients().size();
        this.agentCount = realEstateAgency.getAgents().size();
        this.propertyCount = realEstateAgency.getApartments().size();
        this.allTransactionIncome = realEstateAgency.getIncome();
    }

    public LocalDateTime getLogTime() {
        return logTime;
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

    public void printInfo() {
        System.out.println("\nCurrent status of agency: ");
        System.out.println("Number of workers: " + this.agentCount);
        System.out.println("Number of clients: " + this.customerCount);
        System.out.println("Number of all apartments: " + this.propertyCount);
        System.out.println("All income: " + this.allTransactionIncome);
        System.out.println("Status from: " + this.logTime);
    }
}
