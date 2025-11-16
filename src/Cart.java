/**
 * Documentation: Cart
 *
 * Module Purpose:
 * Represents an individual item in the user's cart and stores the name and price of that item.
 * Class is used by CartUI and CartDAO classes to display, calculate totals, and manage user's selected items.
 */



public class Cart {
    private String name;
    private double price;

    /**
     * Creates cart item with name and price
     *
     * @param name the name of the item
     * @param price the price of the itme
     */

    public Cart(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Get the name of the item
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Get the price of the item
     *
     * @return the price of the item
     */

    public double getPrice() {
        return price;
    }

    /**
     * Return formatted string representation of the item
     *
     * @return a string "name - $price"
     */
    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", price);
    }
}