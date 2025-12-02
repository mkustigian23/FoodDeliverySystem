/**
 * Documentation:
 *
 * This class represents an address and has getters and setters for its components.
 *
 * Key Fields:
 * - streetName: The name of the street
 * - streetNum: The street number
 * - city: The city name
 * - state: The state name
 * - buildingType: Type of building
 * - buildingName: Name of the building
 * - apt: Apartment number
 */

public class Address {
    private String streetName;
    private double streetNum;

    private String city;
    private String state;
    private String zipCode;

    private String buildingType;

    /**
     * Getter methods for retrieving address information
     */
    public String getStreetName() { return streetName; }
    public double getStreetNum() { return streetNum; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
    public String getBuildingType() { return buildingType; }

    /**
     * Setter methods for updating address information
     */
    public void setStreetName(String streetName) { this.streetName = streetName; }
    public void setStreetNum(double streetNum) { this.streetNum = streetNum; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    public void setBuildingType(String buildingType) { this.buildingType = buildingType; }
}




