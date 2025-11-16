/**
 * Documentation: Menu
 *
 * This class represents a menu item in a restaurant. It stores the restaurant ID, item name, price, and image path
 *
 * Module Purpose:
 * - Represents a menu item that is displayed in the UI.
 * - Provides access to item info like the name, price, and image
 * - Can be used to add items to the cart
 *
 * Key Method:
 * Menu(int restaurantId, String name, double price, String menu_imagePath):
 * - Constructor to initialize a menu item with the restaurant ID, name, price, and image path
 */

public class Menu {
    private int restaurantId;
    private String name;
    private double price;
    private String menu_imagePath;

    /**
     *Constructor to create a new menu item.
     *
     * @param restaurantId The id of the restaurant the item is under.
     * @param name The name of the menu item
     * @param price The price of the menu item
     * @param menu_imagePath Path to the image of the menu item
     */
    public Menu(int restaurantId, String name, double price, String menu_imagePath) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
        this.menu_imagePath = menu_imagePath;
    }

    /**
     * Gets restaurant ID of the menu item
     *
     * @return the restaurant ID
     */

    public int getRestaurantId() {
        return restaurantId;
    }

    /**
     * Gets name of menu item
     *
     * @return the name of the item
     */

    public String getName() {
        return name;
    }

    /**
     * Gets price of menu item
     *
     * @return the price of the item
     */

    public double getPrice() {
        return price;
    }

    /**
     * Gets path of image for the menu item
     *
     * @return the image path for the menu item
     */

    public String getMenu_imagePath() {
        return menu_imagePath;
    }

    /**
     * Returns a string representation of the menu item
     *
     * @return Formatted String: "Name - $Price"
     */

    @Override
    public String toString() {
        return String.format("%s - $%.2f", name, price);
    }
}
