package realestate.agency;

import custom.CustomLinkedList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realestate.interfaces.InformationPrinting;

import java.time.LocalDateTime;

public final class AgencyStatus implements InformationPrinting {
    private final static Logger LOGGER = LogManager.getLogger(AgencyStatus.class);
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
        LOGGER.info("Current status of agency: " + "\n"
                + "Number of workers: " + this.agentCount + "\n"
                + "Number of clients: " + this.customerCount + "\n"
                + "Number of all apartments: " + this.propertyCount + "\n"
                + "All income: " + this.allTransactionIncome + "\n"
                + "Status from: " + this.logTime);
    }
}
