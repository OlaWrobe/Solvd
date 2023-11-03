package realestate;

import java.util.List;

public class Agent extends Person {
    private static int lastAgentId = 0;
    private int agentId;
    private List<CityLocation> areasOfWork;
    private int salary;

    public Agent(String name, String surname, ContactInformation contact, List<CityLocation> areasOfWork, int salary) {
        super(name, surname, contact);
        this.agentId = ++lastAgentId;
        this.areasOfWork = areasOfWork;
        this.salary = salary;
    }

    public int getAgentId() {
        return agentId;
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

    @Override
    public void printInfo() {
        System.out.println("Agent name: " + this.name + " " + this.surname + "." + " Phone number: " + this.contact.getPhoneNumber() + " Email: " + this.contact.getEmail());
        System.out.println("Areas of work: ");
        for (CityLocation area : areasOfWork) {
            System.out.print(area.getCityName() + " ");
        }
    }

    @Override
    public String toString() {
        return "Person that is an agent with id " + this.agentId;
    }

}
