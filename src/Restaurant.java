import java.util.HashMap;

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

    }


