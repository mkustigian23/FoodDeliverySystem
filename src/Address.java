public class Address {
    private String streetName;

    private String city;

    private String state;

    private String bulidingType;
    private String buildingName;

    private double Apt;
    private double streetNum;

    public String getStreetName() {
        return streetName;
    }

    public double getStreetNum() {
        return streetNum;
    }

    public String getBulidingType() {
        return bulidingType;
    }

    public double getApt() {
        return Apt;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setStreetNum(double streetNum) {
        this.streetNum = streetNum;
    }

    public void setBulidingType(String bulidingType) {
        this.bulidingType = bulidingType;
    }

    public void setApt(double apt) {
        Apt = apt;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }
}




