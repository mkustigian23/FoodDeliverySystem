import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String, Double> BridgeMart = new HashMap<>();
        BridgeMart.put("Bacon Egg and Cheese", 8.99);
        BridgeMart.put("Chicken Club", 9.49);
        BridgeMart.put("Salad", 6.99);

        HashMap<String, Double> BearsDen = new HashMap<>();
        BearsDen.put("California Roll", 7.99);
        BearsDen.put("Chicken Tacos", 9.49);
        BearsDen.put("Hamburger", 5.99);

        HashMap<String, Double> CrimsonDining = new HashMap<>();
        CrimsonDining.put("Cheeseburger", 7.99);
        CrimsonDining.put("Ice cream", 5.49);
        CrimsonDining.put("Pizza Slice", 4.49);

        HashMap<String, Double> Greyhound = new HashMap<>();
        Greyhound.put("Adult Beverage", 2.99);
        Greyhound.put("Quesadilla", 10.49);
        Greyhound.put("Chips & Salsa", 5.29);

        HashMap<String, Double> Fiesta = new HashMap<>();
        Fiesta.put("Burrito", 14.49);
        Fiesta.put("Bowl", 14.49);
        Fiesta.put("Tres Leches", 5.99);

        // restaurant objects
        Restaurant r1 = new Restaurant("BridgeMart", "169 Spring St", BridgeMart);
        Restaurant r2 = new Restaurant("Bear's Den", "67 Park Ave", BearsDen);
        Restaurant r3 = new Restaurant("Crimson Dining", "125 Burril Ave", CrimsonDining);
        Restaurant r4 = new Restaurant("Greyhound", "39 Broad St", Greyhound);
        Restaurant r5 = new Restaurant("Fiesta", "16 Central Sq", Fiesta);

        //  Display their menus
        r1.displayMenu();
        r2.displayMenu();
        r3.displayMenu();
        r4.displayMenu();
        r5.displayMenu();
    }
}