import java.util.HashMap;
import java.util.Scanner;

 public class Restaurant {

        private  String name;
        private String address;
        private HashMap<String, Double> menu;

        //  Constructor
        public Restaurant(String name, String address, HashMap<String, Double> menu) {
            this.name = name;
            this.address = address;
            this.menu = menu;
        }

        //  Getters
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

        public void addItems(Scanner input) {
            System.out.println("\nEnter food item to " + name);
            String itemName;
            double price;

            while(true) {
                System.out.println("Add food item/press return key when all set: ");
                itemName = input.nextLine();
                if (itemName.isEmpty()) {
                    break;
                }
                System.out.println("Enter price: ");
                price = input.nextDouble();
                input.nextLine();
                menu.put(itemName, price);
                System.out.println(itemName + " is added!");
            }

        }

        public void removeItems(Scanner input) {
            System.out.println("\nEnter food item you want to remove from " + name);
            String removeItem;

            while(true) {
                System.out.println("Remove food item/press return key when all set: ");
                removeItem = input.nextLine();
                if (removeItem.isEmpty()) {
                    break;
                }
                menu.remove(removeItem);
                System.out.println(removeItem + " is removed!");
            }

        }

    }


