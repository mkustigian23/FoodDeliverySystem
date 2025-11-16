/**
 * Documentation : CartDAO
 *
 * This class acts as a DAO for the user's cart. It manages the cart items and provides methods to add, remove,
 * retrieve items, and get the total.
 *
 * Module Purpose:
 * - Store and manage the list of items that that user adds to the cart
 * - Provide access to cart contents
 * - Calculate the total price of all the items the user added to the cart
 */


import java.util.ArrayList;
import java.util.List;

    public class CartDAO {
        private static final List<Menu> cartItems = new ArrayList<>();

        /**
         * Adds the item the user wants to the cart
         *
         * @param item the menu item the user wants to add to the cart
         */
        public static void addItem(Menu item) {
            cartItems.add(item);
        }

        /**
         * Removes the item from the cart
         *
         * @param item the menu item the user wants to remove from the cart
         */
        public static void removeItem(Menu item) {
            cartItems.remove(item);
        }

        /**
         * Returns the current list of menu items in the cart
         *
         * @return the list of items in the cart
         */
        public static List<Menu> getItems() {
            return cartItems;
        }

        /**
         * Calculates the total amount of all the items in the cart
         *
         * @return the total cost of all the items inn the cart
         */
        public static double getTotal() {
            return cartItems.stream().mapToDouble(Menu::getPrice).sum();
        }
    }