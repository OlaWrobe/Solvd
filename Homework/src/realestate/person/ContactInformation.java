package realestate.person;

public class ContactInformation {
    private String phoneNumber;
    private String email;
    private CityLocation cityLocation;
    private String street;
    private String additionalInfoStreet;

    public ContactInformation(String phoneNumber, String email, CityLocation cityLocation, String street, String additionalInfoStreet) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cityLocation = cityLocation;
        this.street = street;
        this.additionalInfoStreet = additionalInfoStreet;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CityLocation getCityLocation() {
        return cityLocation;
    }

    public void setCityLocation(CityLocation cityLocation) {
        this.cityLocation = cityLocation;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAdditionalInfoStreet() {
        return additionalInfoStreet;
    }

    public void setAdditionalInfoStreet(String additionalInfoStreet) {
        this.additionalInfoStreet = additionalInfoStreet;
    }

    @Override
    public String toString() {
        return "Phone number: " + this.phoneNumber + "Email: " + this.email + "Localization: " + this.cityLocation.toString() + " Street: " + this.street + this.additionalInfoStreet;
    }
}
