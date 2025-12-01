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
        setBounds(300, 90, 700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setLayout(new BorderLayout());
        c = getContentPane();



        driverDAO = new DriverDAO();
        deliveryDAO = new DeliveryDAO();

        // Top panel: Select driver
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Select Driver:"));

        driverCombo = new JComboBox<>();
        topPanel.add(driverCombo);

        refreshButton = new JButton("Refresh Deliveries");
        topPanel.add(refreshButton);

        add(topPanel, BorderLayout.NORTH);

        // Center panel: Delivery list
        deliveriesArea = new JTextArea();
        deliveriesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(deliveriesArea);
        add(scrollPane, BorderLayout.CENTER);

        loadDrivers();

        // Action: refresh deliveries when driver selected or refresh button clicked
        ActionListener refreshListener = e -> loadDeliveries();
        driverCombo.addActionListener(refreshListener);
        refreshButton.addActionListener(refreshListener);

        setVisible(true);
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
