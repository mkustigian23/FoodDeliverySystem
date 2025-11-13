import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CustomerFrame extends JFrame {
    private JComboBox<String> restaurant;
    private JComboBox<Menu> menu;
    private JButton orderButton, doneButton;
    private ArrayList<Restaurant> restaurantList;

    public CustomerFrame() {
        setTitle("BSU Eats - Choose Restaurant");
        setBounds(300, 90, 700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel restaurantLabel = new JLabel("Select a Restaurant:");
        restaurant = new JComboBox<>();
        JLabel menuLabel = new JLabel("Select a Menu Item:");
        menu = new JComboBox<>();

        orderButton = new JButton("Order Now");
        doneButton = new JButton("Done");

        add(restaurantLabel);
        add(restaurant);
        add(menuLabel);
        add(menu);
        add(orderButton);
        add(doneButton);

        // Load restaurants from database
        try {
            RestaurantDAO dao = new RestaurantDAO();
            restaurantList = new ArrayList<>(); //dao.getAll()
            for (Restaurant r : restaurantList) {
                restaurant.addItem(r.getName());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading restaurants: " + e.getMessage());
            restaurantList = new ArrayList<>();
        }

        // When restaurant changes, load corresponding menu
        restaurant.addActionListener(e -> {
            String selected = (String) restaurant.getSelectedItem();
            menu.removeAllItems();

            if (selected == null) return;

            ArrayList<Menu> menuList = new ArrayList<>();

            if (selected.equals("Pasta Palace")) {
                menuList.add(new Menu("Spaghetti", 13.00));
                menuList.add(new Menu("Alfredo", 15.00));
                menuList.add(new Menu("Garlic Bread", 7.00));
            } else if (selected.equals("Burger Barn")) {
                menuList.add(new Menu("Cheeseburger", 10.00));
                menuList.add(new Menu("Fries", 4.00));
                menuList.add(new Menu("Milkshake", 6.00));
            }

            for (Menu m : menuList) {
                menu.addItem(m);
            }
        });

        // Order button, go to Payment
        orderButton.addActionListener(e -> {
            Menu selectedItem = (Menu) menu.getSelectedItem();
            if (selectedItem == null) {
                JOptionPane.showMessageDialog(this, "Please select a menu item first");
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Order added to cart: " + selectedItem.getItemName() +
                            String.format("\nTotal: $%.2f", selectedItem.getPrice()));


            // Open payment frame
            new PaymentFrame();
            dispose();
        });

        doneButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Thank you for using BSU Eats!");
            dispose();
        });

        setVisible(true);
    }
}
