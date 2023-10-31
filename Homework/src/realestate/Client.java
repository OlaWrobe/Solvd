package realestate;

public class Client {
    private static int lastClientId = 0;
    private int clientId;
    private String name;
    private String surname;
    private ContactInformation contact;
    private ClientForm clientForm;

    // Constructor
    public Client(String name, String surname, ContactInformation contact, ClientForm clientForm) {
        this.clientId = ++lastClientId;
        this.name = name;
        this.surname = surname;
        this.contact = contact;
        this.clientForm = clientForm;
    }

    public int getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ContactInformation getContact() {
        return contact;
    }

    public void setContact(ContactInformation contact) {
        this.contact = contact;
    }

    public ClientForm getClientForm() {
        return clientForm;
    }

    public void setClientForm(ClientForm clientForm) {
        this.clientForm = clientForm;
    }

}