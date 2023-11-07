package realestate.person;

import realestate.interfaces.InformationPrinting;

public class Client extends Person implements InformationPrinting {
    private static int lastClientId = 0;
    private int clientId;
    private ClientForm clientForm;

    // Constructor
    public Client(String name, String surname, ContactInformation contact, ClientForm clientForm) {
        super(name, surname, contact);
        this.clientId = ++lastClientId;
        this.clientForm = clientForm;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getClientId() {
        return clientId;
    }

    public ClientForm getClientForm() {
        return clientForm;
    }

    public void setClientForm(ClientForm clientForm) {
        this.clientForm = clientForm;
    }

    @Override
    public void printInfo() {
        System.out.println("Client name: " + this.name + " " + this.surname + "." + " Phone number: " + this.contact.getPhoneNumber() + " Email: " + this.contact.getEmail());
        System.out.println("Looking for apartment for " + this.clientForm.getTransactionType() + " with "
                + this.clientForm.getNumberOfBedrooms() + " bedrooms and "
                + this.clientForm.getNumberOfBathrooms() + " bathrooms");
        if (this.clientForm.getNeedsParking()) {
            System.out.println("With parking");
        } else {
            System.out.println("Without parking");
        }
        System.out.println("Budget: " + this.clientForm.getBudget() + "\n");
    }
}
