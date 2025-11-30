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
 * CartFrame(JFrame parent):
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

public class CartFrame extends JDialog {

    private JPanel itemsPanel;
    private JLabel totalPriceLabel;

    /**
     * Constructs the CartUI window that shows all items that are added to the cart.
     * Sets up the interface, total price, and back/checkout button
     *
     * @param parent refers to the parent window that opens the cart
     */
    public CartFrame(JFrame parent) {
        setTitle("Your Cart");
        setSize(800, 600);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel with background color
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(173, 216, 230)); // light blue
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel("Your Cart", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(title, BorderLayout.NORTH);

        // Scrollable items panel
        itemsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        itemsPanel.setOpaque(false); // keep background consistent
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.add(new JScrollPane(itemsPanel), BorderLayout.CENTER);

        // Bottom section with total and buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        totalPriceLabel = new JLabel("Total: $" + String.format("%.2f", CartDAO.getTotal()));
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalPriceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(totalPriceLabel, BorderLayout.WEST);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(e -> {
            new CustomerFrame(); // replace with real restaurant data
            dispose();
        });

        //logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            CartDAO.clearCart();
            // Close all open dialogs if needed
            for (Window window : Window.getWindows()) {
                if (window instanceof JDialog) {
                    window.dispose();
                }
            }

            LoginDAO.logout();
            dispose();                 // close CustomerFrame
            SwingUtilities.invokeLater(LoginFrame::new);
        });

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        checkoutButton.addActionListener(e -> goToAddress());

        buttonPanel.add(backButton);
        buttonPanel.add(checkoutButton);

        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        add(logoutButton, BorderLayout.SOUTH);

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
            JLabel emptyLabel = new JLabel("Your cart is empty.", JLabel.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            itemsPanel.add(emptyLabel);
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
        row.setOpaque(false);
        row.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        row.setPreferredSize(new Dimension(400, 50));

        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        row.add(nameLabel, BorderLayout.WEST);

        JLabel priceLabel = new JLabel("$" + String.format("%.2f", item.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row.add(priceLabel, BorderLayout.CENTER);

        JButton removeBtn = new JButton("Remove");
        removeBtn.setFont(new Font("Arial", Font.PLAIN, 14));
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