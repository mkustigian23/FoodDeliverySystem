/**
 * Documentation: Cart Frame
 *
 * This class represents the cart interface where users can view all the items they have added to the cart.
 * They can remove items from the cart, see the total cost, go back to the restaurant selection screen, or hit
 * the next button go to the payment screen.
 *
 * Module Purpose:
 * - Displays all items stored in CartDAO
 * - Lets users remove items
 * - Shows the total price and updates if someone removes an item or adds another item
 * - Allows the user to go back to the previous page or go to the payment page
 *
 * Key Methods:
 * CartUI():
 * - Constructor that builds the carts UI, loads items, and sets up the total price, item list, and back/checkout buttons
 *
 * refreshCartDisplay():
 * - Reloads the carts content and recalculates the total amount when an item is removed
 * - called when an item is removed
 *
 * createCartItemRow(Menu item):
 * - Creates a row in the UI which contains the menu item's name, price, and remove button.
 *
 * goToAddress():
 * - Takes user from the cart ui to the address frame
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CartFrame extends BaseFrame {

    private JPanel itemsPanel;
    private JLabel totalPriceLabel;

    /**
     * Constructs the CartUI window that shows all items that are added to the cart.
     * Sets up the interface, total price, and back/checkout button
     *
     */
    public CartFrame() {
        setTitle("Your Cart");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Your Cart", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Scrollable items panel
        itemsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(new JScrollPane(itemsPanel), BorderLayout.CENTER);

        // Bottom section with total and buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());

        totalPriceLabel = new JLabel("Total: $" + String.format("%.2f", CartDAO.getTotal()));
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalPriceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(totalPriceLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new CustomerFrame(); // replace with real restaurant data
            dispose();
        });

        // Checkout button
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> goToAddress());

        // Back button
        buttonPanel.add(backButton);
        buttonPanel.add(checkoutButton);

        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        refreshCartDisplay();

        setVisible(true);

    }

    /**
     * Refreshes cart interface by clearing the existing items, reloading items from CartDAO, and updating the total
     * price.
     *
     * This method is called when the items in the cart change like if an item is removed from the cart
     */

    private void refreshCartDisplay() {
        itemsPanel.removeAll();

        List<Menu> items = CartDAO.getItems();

        if (items.isEmpty()) {
            itemsPanel.add(new JLabel("Your cart is empty.", JLabel.CENTER));
        } else {
            for (Menu item : items) {
                itemsPanel.add(createCartItemRow(item));
            }
        }

        totalPriceLabel.setText("Total: $" + String.format("%.2f", CartDAO.getTotal()));

        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    /**
     * Creates a single row in the cart display for a menu item.
     * The row has the item name, item price, and a remove button.
     *
     * @param item The menu object that represents the food item in the cart
     * @return the row with the formatted item information
     */
    private JPanel createCartItemRow(Menu item) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        row.setPreferredSize(new Dimension(400, 50));

        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        row.add(nameLabel, BorderLayout.WEST);

        JLabel priceLabel = new JLabel("$" + String.format("%.2f", item.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row.add(priceLabel, BorderLayout.CENTER);

        JButton removeBtn = new JButton("Remove");
        removeBtn.addActionListener((ActionEvent e) -> {
            CartDAO.removeItem(item);
            refreshCartDisplay();
        });

        row.add(removeBtn, BorderLayout.EAST);

        return row;
    }
    /**
     * Takes user from the cart ui to the address frame
     */
    private void goToAddress() {
        new AddressFrame();  // open next frame
        dispose();           // close the cart frame
    }
}