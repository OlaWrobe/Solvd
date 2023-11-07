package realestate.person;

import realestate.interfaces.InformationPrinting;
import realestate.interfaces.PersonData;

public abstract class Person implements InformationPrinting, PersonData {
    protected String name;
    protected String surname;
    protected ContactInformation contact;

    public Person(String name, String surname, ContactInformation contact) {
        this.name = name;
        this.surname = surname;
        this.contact = contact;
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

    public abstract void printInfo();
}
