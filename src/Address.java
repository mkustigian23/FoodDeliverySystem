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
    private String zipCode;        // ✅ added to support zip code validation

    private String buildingType;   // ✅ spelling corrected
    private String buildingName;
    private double apt;

    /**
     * Getter methods for retrieving address information
     */
    public String getStreetName() { return streetName; }
    public double getStreetNum() { return streetNum; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZipCode() { return zipCode; }
    public String getBuildingType() { return buildingType; }
    public String getBuildingName() { return buildingName; }
    public double getApt() { return apt; }

    /**
     * Setter methods for updating address information
     */
    public void setStreetName(String streetName) { this.streetName = streetName; }
    public void setStreetNum(double streetNum) { this.streetNum = streetNum; }
    public void setCity(String city) { this.city = city; }
    public void setState(String state) { this.state = state; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }
    public void setBuildingType(String buildingType) { this.buildingType = buildingType; } 
    public void setBuildingName(String buildingName) { this.buildingName = buildingName; }
    public void setApt(double apt) { this.apt = apt; }
}




