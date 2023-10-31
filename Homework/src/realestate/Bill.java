package realestate;

public class Bill {
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
            System.out.println("Incorrect purpose");
        }
        return this.bill;
    }

}
