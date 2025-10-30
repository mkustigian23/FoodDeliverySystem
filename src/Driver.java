
public class Driver {
    private int id;
    private String name;
    private String licenseNumber;

    public Driver(int id, String name, String licenseNumber) {
        this.id = id;
        this.name = name;
        this.licenseNumber = licenseNumber;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLicenseNumber() { return licenseNumber; }

    @Override
    public String toString() {
        return "Driver[" + id + "] " + name + " - License: " + licenseNumber;
    }
}

