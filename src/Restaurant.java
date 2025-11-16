/**
 * Documentation: Restaurant
 *
 * This class represents a restaurant in the system and provides access to restaurant information
 * like ID, name, location, and image.
 *
 * Key Fields:
 * id: identifier for the restaurant
 * name: name of the restaurant
 * location: address of location
 * image: Path to the image of restaurant
 *
 */
public class Restaurant {
    private int id;
    private String name;
    private String location;
    private String imagePath;

    /**
     * Constructor to create a new restaurant
     *
     * @param id restaurant ID
     * @param name name of restaurant
     * @param location location of restaurant
     * @param imagePath path to the image of the restaurant
     */

    public Restaurant(int id, String name, String location, String imagePath) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.imagePath = imagePath;
    }

    /**
     * Get the id of the restaurant
     *
     * @return return the id of the restaurant
     */
    public int getId() { return id; }

    /**
     * Get the name of the restaurant
     *
     * @return return restaurant's name
     */
    public String getName() { return name; }

    /**
     * Get location of the restaurant
     *
     * @return return location of the restaurant
     */
    public String getLocation() { return location; }

    /**
     * get the path to the restaurant's image
     *
     * @return return the image path
     */
    public String getImagePath() { return imagePath;}

    /**
     * Returns a string representation "Restaurant[id] name - location"
     *
     * @return String representation of the restaurant
     */
    @Override
    public String toString() {
        return "Restaurant[" + id + "] " + name + " - " + location;
    }
}


