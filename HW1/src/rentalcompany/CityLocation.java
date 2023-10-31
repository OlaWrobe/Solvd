package rentalcompany;

public class CityLocation {
    private String cityName;
    private String voivodeship;
    private String zipCode;

    public CityLocation(String cityName, String voivodeship, String zipCode) {
        this.cityName = cityName;
        this.voivodeship = voivodeship;
        this.zipCode = zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
