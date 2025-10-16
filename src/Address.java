public class Address {
    private String streetName;
    private double streetNum;
    private String bulidingType;
    private double Apt;
    private String buildingName;

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
}
