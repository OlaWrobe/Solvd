package realestate.transactions;

import realestate.interfaces.Billing;
import realestate.transactions.TransactionType;

public class Bill implements Billing {
    static double consultation = 500;
    static double tenantRepresentation = 200;
    static double fitOut = 700;
    static double buyTransaction = 2000;
    static double rentTransaction = 500;
    private double bill;

    public Bill() {
        this.bill = 0;
    }

    public double getBill() {
        return bill;
    }

    public void setBill(double bill) {
        this.bill = bill;
    }

    public double calculateBill(TransactionType transactionType, double apartmentCost) {
        this.bill = consultation + tenantRepresentation + fitOut + apartmentCost;
        if (transactionType.equals(TransactionType.BUY)) {
            this.bill += buyTransaction;
        } else if (transactionType.equals(TransactionType.RENTAL)) {
            this.bill += rentTransaction;
        } else {
            System.out.println("Incorrect transaction type");
        }
        return this.bill;
    }

    static public void printPriceList() {
        System.out.println("consultation: " + consultation);
        System.out.println("tenant representation:" + tenantRepresentation);
        System.out.println("fit out " + fitOut);
        System.out.println("Buy mediations: " + buyTransaction);
        System.out.println("Rent mediations: " + rentTransaction);
    }
}
