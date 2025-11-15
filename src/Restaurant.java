
public class Restaurant {
    private int id;
    private String name;
    private String location;
    private String imagePath;

    public Restaurant(int id, String name, String location, String imagePath) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.imagePath = imagePath;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }

    public String getImagePath() { return imagePath;}

    @Override
    public String toString() {
        return "Restaurant[" + id + "] " + name + " - " + location;
    }
}


