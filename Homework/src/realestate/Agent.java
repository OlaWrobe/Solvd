package realestate;

import java.util.List;

public class Agent extends Person {
    private static int lastAgentId = 0;
    private int agentId;
    private List<CityLocation> areasOfWork;
    private int salary;

    public Agent(String name, String surname, ContactInformation contact, List<CityLocation> areasOfWork, int salary) {
        this.agentId = ++lastAgentId; // Automatically increment agentId upon object creation
        this.name = name;
        this.surname = surname;
        this.contact = contact;
        this.areasOfWork = areasOfWork;
        this.salary = salary;
    }

    public int getAgentId() {
        return agentId;
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

    public List<CityLocation> getAreasOfWork() {
        return areasOfWork;
    }

    public void setAreasOfWork(List<CityLocation> areasOfWork) {
        this.areasOfWork = areasOfWork;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void printAgentInfo() {
        System.out.println("Agent name: " + this.name + " " + this.surname + "." + " Phone number: " + this.contact.getPhoneNumber() + " Email: " + this.contact.getEmail());
        System.out.println("Areas of work: ");
        for (CityLocation area : areasOfWork) {
            System.out.print(area.getCityName() + " ");
        }
    }

}
