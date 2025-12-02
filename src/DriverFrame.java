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
    private JList<String> deliveriesList;   // changed from JTextArea to JList for clickable jobs
    private JButton refreshButton;
    private DefaultListModel<String> deliveriesModel;

    private DriverDAO driverDAO;
    private DeliveryDAO deliveryDAO;

    /**
     *
     * Constructs the driver frame window and initializes all the UI components
     */
    public DriverFrame() {
        setTitle("BSU Eats - Driver Dashboard");
        setBounds(300, 90, 700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Light blue background applied to content pane
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.setBackground(new Color(173, 216, 230)); // light blue

        driverDAO = new DriverDAO();
        deliveryDAO = new DeliveryDAO();

        // Top panel: Select driver
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setOpaque(false); // keep background consistent
        topPanel.add(new JLabel("Select Driver:"));

        driverCombo = new JComboBox<>();
        topPanel.add(driverCombo);

        refreshButton = new JButton("Refresh Deliveries");
        topPanel.add(refreshButton);

        add(topPanel, BorderLayout.NORTH);

        // Center panel: Delivery list
        // Changed to JList so driver can click on jobs to accept them
        deliveriesModel = new DefaultListModel<>();
        deliveriesList = new JList<>(deliveriesModel);
        deliveriesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        deliveriesList.setFont(new Font("Arial", Font.PLAIN, 14));
        deliveriesList.setBackground(new Color(224, 240, 255)); // lighter blue for list

        JScrollPane scrollPane = new JScrollPane(deliveriesList);
        add(scrollPane, BorderLayout.CENTER);

        loadDrivers();

        // Action: refresh deliveries when driver selected or refresh button clicked
        ActionListener refreshListener = e -> loadDeliveries();
        driverCombo.addActionListener(refreshListener);
        refreshButton.addActionListener(refreshListener);

        // Action: driver clicks on a job to accept it
        deliveriesList.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // double-click to accept
                    String selectedJob = deliveriesList.getSelectedValue();
                    if (selectedJob != null && selectedJob.startsWith("Delivery #")) {
                        int driverId = parseDriverId();
                        int deliveryId = parseDeliveryId(selectedJob);
                        try {
                            deliveryDAO.acceptJob(driverId, deliveryId);
                            JOptionPane.showMessageDialog(DriverFrame.this,
                                    "Job accepted: " + selectedJob);
                            loadDeliveries(); // refresh list after accepting
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(DriverFrame.this,
                                    "Error accepting job: " + ex.getMessage());
                        }
                    }
                }
            }
        });

        setVisible(true);
    }

    /**
     * Overloaded constructor:
     * Allows cart items and customer address to be merged into the driver dashboard
     */
    public DriverFrame(List<Menu> cartItems, Address address) {
        this(); // call the default constructor to set up UI

        deliveriesModel.addElement("=== New Order ===");
        if (cartItems == null || cartItems.isEmpty()) {
            deliveriesModel.addElement("No items purchased.");
        } else {
            for (Menu item : cartItems) {
                deliveriesModel.addElement(item.getName() + " - $" + String.format("%.2f", item.getPrice()));
            }
        }

        // Show customer's address information for the driver
        deliveriesModel.addElement("=== Delivery Address ===");
        deliveriesModel.addElement("Street Name: " + address.getStreetName());
        deliveriesModel.addElement("City: " + address.getCity());
        deliveriesModel.addElement("State: " + address.getState());
        deliveriesModel.addElement("Zip Code: " + address.getZipCode());
        deliveriesModel.addElement("Building Type: " + address.getBuildingType());
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
        deliveriesModel.clear();
        String selected = (String) driverCombo.getSelectedItem();
        if (selected == null) return;

        // Extract driver id from string "1: John Doe"
        int driverId = parseDriverId();

        try {
            List<String> deliveries = deliveryDAO.getDeliveriesByDriver(driverId);
            if (deliveries.isEmpty()) {
                deliveriesModel.addElement("No deliveries assigned yet.");
            } else {
                for (String d : deliveries) {
                    deliveriesModel.addElement(d);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading deliveries: " + e.getMessage());
        }
    }

    private int parseDriverId() {
        String selected = (String) driverCombo.getSelectedItem();
        if (selected == null) return -1;
        return Integer.parseInt(selected.split(":")[0].trim());
    }

    private int parseDeliveryId(String jobString) {
        // jobString format: "Delivery #12 | Customer: ... "
        try {
            String idPart = jobString.split("#")[1].split("\\|")[0].trim();
            return Integer.parseInt(idPart);
        } catch (Exception e) {
            return -1;
        }
    }
}