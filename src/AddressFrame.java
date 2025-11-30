/**
 * Documentation: AddressFrame
 *
 * This class is the UI for the address page for entering address information before going to the payment
 * page.
 *
 * Module Purpose:
 * Collects user delivery information like street name, city, state, zip code, and building type.
 * Validates that all fields are entered correctly or the user can't proceed.
 * Allows user to return to the cart if needed
 *
 * Key Methods:
 * AddressFrame():
 * - Constructor initializes the address window
 * - Creates labels, text fields and buttons
 *
 * continueToPayment():
 * - When the user clicks the continue button
 * - Validates that all fields are filled
 * - Closes the AddressFrame
 *
 * goBackToCart():
 * - When the back button is pressed
 * - returns the user to cart UI
 */

import javax.swing.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AddressFrame extends JFrame {

    private JTextField streetName;
    private JTextField cityName;
    private JTextField stateName;
    private JTextField zipCode;
    private JTextField buildingType;

    private JButton continueButton;
    private JButton backButton;

    /**
     * Creates new Address Frame window that collects address information
     * Initializes labels, text fields, buttons, and layouts
     */
    public AddressFrame() {
        setTitle("Address Information");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with background color
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(173, 216, 230)); // light blue
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Enter Address Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel for form fields
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setOpaque(false); // keep background consistent

        Font labelFont = new Font("Arial", Font.PLAIN, 16);

        JLabel streetLabel = new JLabel("Street Name:");
        streetLabel.setFont(labelFont);
        panel.add(streetLabel);
        streetName = new JTextField();
        panel.add(streetName);

        JLabel cityLabel = new JLabel("City:");
        cityLabel.setFont(labelFont);
        panel.add(cityLabel);
        cityName = new JTextField();
        panel.add(cityName);

        JLabel stateLabel = new JLabel("State:");
        stateLabel.setFont(labelFont);
        panel.add(stateLabel);
        stateName = new JTextField();
        panel.add(stateName);

        JLabel zipLabel = new JLabel("Zip Code:");
        zipLabel.setFont(labelFont);
        panel.add(zipLabel);
        zipCode = new JTextField();
        panel.add(zipCode);

        JLabel buildingLabel = new JLabel("Building Type:");
        buildingLabel.setFont(labelFont);
        panel.add(buildingLabel);
        buildingType = new JTextField();
        panel.add(buildingType);

        mainPanel.add(panel, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        continueButton = new JButton("Continue");
        continueButton.setFont(new Font("Arial", Font.BOLD, 16));
        continueButton.addActionListener(this::continueToPayment);
        buttonPanel.add(continueButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(e -> goBackToCart());
        buttonPanel.add(backButton);

        //Logout Button
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

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        add(logoutButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Validates all address fields and continues to payment page as long as the user input all the correct
     * information
     * @param e the ActionEvent becomes triggered when the user clicks the continue to payment button
     */
    private void continueToPayment(ActionEvent e) {
        // Validate fields
        if (e.getSource() == continueButton) {
            if (streetName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your Street name.");
                return;
            }
            if (cityName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your City name.");
                return;
            }
            if (stateName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your State name.");
                return;
            }
            String exp = zipCode.getText().trim();
            if (!exp.matches("\\d{5}")) {
                JOptionPane.showMessageDialog(this, "Zip Code must be 5 digits.");
                return;
            }
            if (buildingType.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your Building Type.");
                return;
            }

        }

        // Create address object
        Address address = new Address();
        address.setStreetName(streetName.getText().trim());
        address.setCity(cityName.getText().trim());
        address.setState(stateName.getText().trim());
        address.setBulidingType(buildingType.getText().trim());

        // Get total from cart
        int total = (int) CartDAO.getTotal();

        // Open PaymentFrame
        new PaymentFrame(total, address);

        // Close this frame
        dispose();
    }

    /**
     * Returns user to Cart UI and closing the current Address Frame
     */
    private void goBackToCart() {
        new CartFrame(this);
        dispose();
    }
}