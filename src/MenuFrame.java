/**
 * Documentation: Menu Frame
 *
 * This class displays the list of menu items for the different restaurants. Users can view the items and
 * add them to their cart. Then they can proceed to the cart checkout screen or go back to the restaurant
 * selection screen
 *
 * Module Purpose:
 * - Shows all the menu items for the specific restaurant.
 * - Allows users to click on the menu items to add them to their cart.
 * - Also has back button to go back to the previous screen if needed or next button to move to the next screen.
 *
 * Key Methods:
 * MenuFrame(int restaurantId, String restaurantName):
 * - Constructor that builds the whole menu UI
 * - Loads menu items from the database and displays them in a grid
 *
 * createMenuItemPanel(Menu item):
 * - Creates a panel for a single food item that includes the price, name, and image
 * - Adds a mouse clicked in order for the item to be added to the cart
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class MenuFrame extends JFrame {

    private int restaurantId;
    private String restaurantName;

    /**
     * Constructs MenuFrame for displaying the menu items for the specific restaurants
     *
     * @param restaurantId The id of the restaurant for the menu being displayed
     * @param restaurantName The name of the restaurant
     */
    public MenuFrame(int restaurantId, String restaurantName) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;

        setTitle("Menu - " + restaurantName);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel with background color
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(173, 216, 230)); // light blue
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title at top
        JLabel titleLabel = new JLabel("Menu for " + restaurantName, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> {
            new CustomerFrame(); // opens the previous page
            dispose();            // closes the current MenuFrame
        });

        // Top panel with title + back button
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Next button
        JButton nextButton = new JButton("Next");
        nextButton.setFont(new Font("Arial", Font.BOLD, 16));
        nextButton.addActionListener(e -> {
            CartFrame cart = new CartFrame(this);
            cart.setVisible(true);
            dispose();
        });

        //logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {

            // Clear cart or session
            CartDAO.clearCart();
            LoginDAO.logout();

            // Close EVERY window except the new LoginFrame
            for (Window w : Window.getWindows()) {
                if (w instanceof JFrame || w instanceof JDialog) {
                    w.dispose();
                }
            }

            // Open LoginFrame AFTER all are closed
            SwingUtilities.invokeLater(() -> {
                new LoginFrame().setVisible(true);
            });
        });

        // Bottom panel with next button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        bottomPanel.setOpaque(false);
        bottomPanel.add(nextButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Panel for menu grid
        JPanel gridPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        gridPanel.setOpaque(false);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        MenuDAO dao = new MenuDAO();
        List<Menu> menuItems;

        try {
            menuItems = dao.getMenuByRestaurant(restaurantId);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading menu: " + e.getMessage());
            menuItems = List.of();
        }

        // Add menu items
        for (Menu item : menuItems) {
            gridPanel.add(createMenuItemPanel(item));
        }

        mainPanel.add(new JScrollPane(gridPanel), BorderLayout.CENTER);

        add(mainPanel);
        add(logoutButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Creates the interactive panel for a menu item and when it's clicked the item is added to the user's cart
     *
     * @param item the menu object containing the food details (name, price, image)
     * @return a JPanel representing this menu item in the UI
     */
    private JPanel createMenuItemPanel(Menu item) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.setPreferredSize(new Dimension(220, 220));
        panel.setOpaque(false);

        // image
        ImageIcon icon;
        try {
            icon = new ImageIcon(item.getMenu_imagePath());
        } catch (Exception e) {
            icon = new ImageIcon("src/default.png");
        }

        Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));
        imgLabel.setHorizontalAlignment(JLabel.CENTER);

        // wrap image in a panel with padding
        JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        imagePanel.setOpaque(false);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        imagePanel.add(imgLabel);

        // name + price
        JLabel nameLabel = new JLabel(item.getName(), JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel priceLabel = new JLabel(String.format("$%.2f", item.getPrice()), JLabel.CENTER);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(imagePanel, BorderLayout.CENTER);
        panel.add(priceLabel, BorderLayout.SOUTH);

        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                CartDAO.addItem(item);
                JOptionPane.showMessageDialog(MenuFrame.this,
                        item.getName() + " added to cart!");
            }
        });

        return panel;
    }
}
