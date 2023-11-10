package realestate.transactions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import realestate.Exceptions.InvalidTransactionTypeException;
import realestate.interfaces.Billing;

public class Bill implements Billing {
    private final static Logger LOGGER = LogManager.getLogger(Bill.class);
    private static final double CONSULTATION = 500;
    private static final double TENANT_REPRESENTATION = 200;
    private static final double FIT_OUT = 700;
    private static final double BUY_TRANSACTION = 2000;
    private static final double RENT_TRANSACTION = 500;
    private double amount;

    public Bill() {
        this.amount = 0;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double calculateBill(TransactionType transactionType, double apartmentCost) {
        this.amount = CONSULTATION + TENANT_REPRESENTATION + FIT_OUT + apartmentCost;
        if (transactionType.equals(TransactionType.BUY)) {
            this.amount += BUY_TRANSACTION;
        } else if (transactionType.equals(TransactionType.RENTAL)) {
            this.amount += RENT_TRANSACTION;
        } else {
            throw new InvalidTransactionTypeException("Incorrect transaction type: " + transactionType);
        }
        return this.amount;
    }

    public static void printPriceList() {
        LOGGER.info("consultation: " + CONSULTATION + "\n"
                + "tenant representation:" + TENANT_REPRESENTATION + "\n"
                + "fit out " + FIT_OUT + "\n"
                + "Buy mediations: " + BUY_TRANSACTION + "\n"
                + "Rent mediations: " + RENT_TRANSACTION);
    }
}
