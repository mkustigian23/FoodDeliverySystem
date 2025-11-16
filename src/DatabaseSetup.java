/**
 * Documentation: DatabaseSetup
 *
 * This class initializes the application's database with restaurants and the menu item that corresponds with it.
 *
 * Module Purpose:
 * - Creates the tables for restaurants and menus
 * - Resets restaurant data by clearing the restaurant table
 * - Inserts restaurants and the menu item that corresponds with it
 *
 * Key Method:
 * main(String[] args):
 * - Creates Restaurant DAO and MenuDAO objects to set up the database
 * - Resets the restaurants table and insert restaurant and menu data
 */

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {
    public static void main(String[] args) {
        try {
            RestaurantDAO rdao = new RestaurantDAO();
            MenuDAO mdao = new MenuDAO();

            rdao.createTable();
            mdao.createTable();

            // Reset restaurant table
            try (Connection conn = DatabaseManager.connect();
                 Statement stmt = conn.createStatement()) {
                stmt.execute("DELETE FROM restaurants");
            }

            // Insert restaurants once for pasta restaurant, burger place, and taco town
            int pastaId = rdao.insert("Pasta Restaurant", "100 Main Street", "src/pasta.png");
            int burgerId = rdao.insert("Burger Place", "45 West Street", "src/burger.png");
            int tacoId   = rdao.insert("Mexican Restaurant", "34 Grove Street", "src/taco.png");

            // Insert menu for the pasta restaurant
            mdao.insert(pastaId, "Spaghetti", 15.00, "src/spaghetti.png");
            mdao.insert(pastaId, "Alfredo", 17.00, "src/alfredo.png");
            mdao.insert(pastaId, "Lasagna", 16.00, "src/lasagna.png");
            mdao.insert(pastaId, "Garlic Bread", 7.00, "src/garlicBread.png");


            // Insert menu for the burger restaurant
            mdao.insert(burgerId, "Cheeseburger", 12.00, "src/cheeseburger.png");
            mdao.insert(burgerId, "Bacon Cheeseburger", 14.00, "src/bacon.png");
            mdao.insert(burgerId, "Fries", 4.00, "src/fries.png");
            mdao.insert(burgerId, "Milk Shake", 6.00, "src/milkShake.png");


            // Insert menu for the taco restaurant
            mdao.insert(tacoId, "Beef Taco", 15.00, "src/beef.png");
            mdao.insert(tacoId, "Chicken Taco", 17.00, "src/chicken.png");
            mdao.insert(tacoId, "Burrito", 16.00, "src/burrito.png");
            mdao.insert(tacoId, "Chips and Guac", 7.00, "src/chips.png");


            System.out.println("Database setup complete!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

