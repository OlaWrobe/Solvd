package realestate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentalTransaction extends Transaction {
    private static int lastTransactionId = 0;
    private int transactionId;
    private LocalDate rentStartDate;
    private LocalDate rentEndDate;

    // Constructor
    public RentalTransaction(Apartment apartment, Agent agent, Client client, LocalDate rentStartDate, LocalDate rentEndDate) {
        super(apartment, agent, client);
        this.transactionId = lastTransactionId;
        this.rentStartDate = rentStartDate;
        this.rentEndDate = rentEndDate;
        lastTransactionId++;
    }

    // Getters and Setters
    public static int getLastTransactionId() {
        return lastTransactionId;
    }

    public static void setLastTransactionId(int lastTransactionId) {
        RentalTransaction.lastTransactionId = lastTransactionId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(LocalDate rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public LocalDate getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(LocalDate rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    @Override
    public void printTransaction() {
        System.out.println("Rent Transaction Id: " + transactionId);
        System.out.println("Parcel Id: " + apartment.getApartmentId() + " in " + apartment.getLocation().getCityName());
        System.out.println("Rented by " + client.getName() + " " + client.getSurname());
        System.out.println("With help of agent " + agent.getName() + " " + agent.getSurname());
        System.out.println("Time of transaction: " + transactionDateTime);
        System.out.println("Rent start date: " + rentStartDate + " Rent end date: " + rentEndDate);
        System.out.println("Total " + bill.getBill());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        if (!super.equals(obj))
            return false;

        RentalTransaction other = (RentalTransaction) obj;

        if (rentStartDate == null) {
            if (other.rentStartDate != null)
                return false;
        } else if (!rentStartDate.equals(other.rentStartDate))
            return false;

        if (rentEndDate == null) {
            if (other.rentEndDate != null)
                return false;
        } else if (!rentEndDate.equals(other.rentEndDate))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (rentStartDate != null ? rentStartDate.hashCode() : 0);
        result = prime * result + (rentEndDate != null ? rentEndDate.hashCode() : 0);
        return result;
    }
}
