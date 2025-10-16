import java.util.HashMap;

public class Restaurant {

    private String name;
    private String[] menuItems;
    private Integer[] menuPrices;
    private String address;
    private HashMap<String, Double> menu;

    //  Constructor
    public Restaurant(String name, String address, HashMap<String, Double> menu) {
        this.name = name;
        this.address = address;
        this.menu = menu;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public HashMap<String, Double> getMenu() {
        return menu;
    }

    // print the restaurant menu
    public void displayMenu() {
        System.out.println("\n--- " + name + " Menu ---");
        for (String item : menu.keySet()) {
            System.out.println(item + " - $" + menu.get(item));
        }
    }
    public void assignItem(String mi, int mp) {
        String[] temp1 = menuItems;
        Integer[] temp2 = menuPrices;
        menuItems = new String[menuItems.length + 1];
        menuPrices = new Integer[menuPrices.length + 1];
        for (int i = 0; i != temp1.length; i++) {
            menuItems[i] = temp1[i];
            menuPrices[i] = temp2[i];
        }
        menuItems[menuItems.length - 1] = mi;
        menuPrices[menuPrices.length - 1] = mp;
    }

    public void itemList() {
        for (int i = 0; i < menuItems.length; i++) {
            System.out.println("Item " + i + " is " + menuItems[i] + " which is priced at " + menuPrices[i] + ".");
        }
    }

    //  main method with the restaurant data
/*    public static void main(String[] args) {

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
    }*/
}