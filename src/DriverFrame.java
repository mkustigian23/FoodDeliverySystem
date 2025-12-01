/**
 * Documentation: DriverFrame
 *
 * This frame represents the UI dashboard for drivers and
 * it allows a driver to view all deliveries assigned to them
 *
 * The module purpose:
 * - Display a dropdown list of all drivers
 * - Show the list of deliveries assigned to the driver
 * - Refresh the deliveries when a driver is selected
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class DriverFrame extends JFrame {
    private JComboBox<String> driverCombo;
    private JTextArea deliveriesArea;
    private JButton refreshButton;

    private DriverDAO driverDAO;
    private DeliveryDAO deliveryDAO;

    protected Container c;
    /**
     *
     * Constructs the driver frame window and initializes all the UI components
     */

    public DriverFrame() {
        setTitle("BSU Eats - Driver Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(300, 90, 900, 600);

        c = getContentPane();
        c.setLayout(new BorderLayout());

        driverDAO = new DriverDAO();
        deliveryDAO = new DeliveryDAO();

        // Main panel with background color
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(173, 216, 230)); // light blue
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Top panel: Select driver
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Select Driver:"));

        driverCombo = new JComboBox<>();
        topPanel.add(driverCombo);

        refreshButton = new JButton("Refresh Deliveries");
        topPanel.add(refreshButton);

        // Center panel: Delivery list
        deliveriesArea = new JTextArea();
        deliveriesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(deliveriesArea);


        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add mainPanel to frame
        c.add(mainPanel, BorderLayout.CENTER);

        loadDrivers();

        // Action: refresh deliveries when driver selected or refresh button clicked
        ActionListener refreshListener = e -> loadDeliveries();
        driverCombo.addActionListener(refreshListener);
        refreshButton.addActionListener(refreshListener);

        //logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            this.setVisible(false);         // hide current frame
            CartDAO.clearCart();
            LoginDAO.logout();

            // dispose any dialogs first
            for (Window w : Window.getWindows()) {
                if (w instanceof JDialog) w.dispose();
            }

            // open login frame
            SwingUtilities.invokeLater(() -> {
                new LoginFrame().setVisible(true);
                this.dispose();             // dispose after login frame visible
            });
        });


        c.add(logoutButton, BorderLayout.SOUTH);

    }

    /**
     * Loads all drivers from the database
     */
    private void loadDrivers() {
        try {
            List<String> drivers = driverDAO.getAll();
            driverCombo.removeAllItems();
            for (String d : drivers) {
                driverCombo.addItem(d);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading drivers: " + e.getMessage());
        }
    }

    /**
     *
     * Loads all deliveries assigned to the selected driver and displays them
     */

    private void loadDeliveries() {
        deliveriesArea.setText("");
        String selected = (String) driverCombo.getSelectedItem();
        if (selected == null) return;

        // Extract driver id from string "1: John Doe"
        int driverId = Integer.parseInt(selected.split(":")[0].trim());

        try {
            List<String> deliveries = deliveryDAO.getDeliveriesByDriver(driverId);
            if (deliveries.isEmpty()) {
                deliveriesArea.setText("No deliveries assigned yet.");
            } else {
                for (String d : deliveries) {
                    deliveriesArea.append(d + "\n");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading deliveries: " + e.getMessage());
        }
    }
}
