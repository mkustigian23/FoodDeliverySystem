/**
 * Documentation: Customer Frame
 *
 * This class represents the UI for selecting a restaurant. It displays the restaurants the user can choose from
 * with images and names. Clicking the restaurant will open the correct menuFrame that corresponds with the
 * correct restaurant.
 *
 * Key Features:
 * - Gets restaurant data from the restaurantDAO
 * - Displays the restaurant name and images
 * - Opens the MenuFrame for the specific restaurant that is clicked on
 *
 * Key methods:
 * CustomerFrame():
 * - Initializes the UI and displays all the restaurant options
 *
 * createRestaurantPanel(Restaurant restaurant):
 * - Creates a JPanel for the restaurant with the name and image and action to click the restaurant.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.sql.SQLException;

public class CustomerFrame extends JFrame {

    private CartFrame cartPanel = new CartFrame();

    /**
     * Constructs the customer Frame and initializes the GUI with all the restaurants from the database
     * Sets up a grid layout for the restaurants.
     */

    public CustomerFrame() {

        setTitle("Select a Restaurant");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(173, 216, 230));

        // Title
        JLabel titleLabel = new JLabel("Select a Restaurant", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        RestaurantDAO dao = new RestaurantDAO();
        List<Restaurant> restaurants;
        try {
            restaurants = dao.getAll(); // fetch from database
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading restaurants: " + e.getMessage());
            restaurants = List.of(); // empty list if error
        }


        for (Restaurant r : restaurants) {
            JPanel panel = createRestaurantPanel(r);
            mainPanel.add(panel);
        }

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Creates a JPanel for a single restaurant
     * Includes the restaurant image and name and click action to open the menuFrame
     *
     * @param restaurant the Restaurant object to display
     * @return a JPanel which contains the restaurant's name and image
     */

    private JPanel createRestaurantPanel(Restaurant restaurant) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel nameLabel = new JLabel(restaurant.getName(), JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Load image
        ImageIcon icon = new ImageIcon(restaurant.getImagePath());
        Image img = icon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(img));

        panel.add(picLabel, BorderLayout.CENTER);
        panel.add(nameLabel, BorderLayout.SOUTH);

        // Make panel clickable
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Single click action
                new MenuFrame(restaurant.getId(), restaurant.getName());
                dispose(); // close CustomerFrame if needed
            }
        });


        return panel;
    }

}
