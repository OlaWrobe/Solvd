package realestate;

public class Client extends Person {
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
        System.out.println("Looking for apartment for " + this.clientForm.getTransactionType() + " with " + this.clientForm.getNumberOfBathrooms() + " bathrooms and " + this.clientForm.getNumberOfBedrooms() + " bedrooms");
        System.out.println("Budget: " + this.clientForm.getBudget());
    }

    @Override
    public String toString() {
        return "Person that is a client with id " + this.clientId;
    }


}
