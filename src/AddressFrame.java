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
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Panel for form fields
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

        panel.add(new JLabel("Street Name:"));
        streetName = new JTextField();
        panel.add(streetName);

        panel.add(new JLabel("City:"));
        cityName = new JTextField();
        panel.add(cityName);

        panel.add(new JLabel("State:"));
        stateName = new JTextField();
        panel.add(stateName);

        panel.add(new JLabel("Zip Code:"));
        zipCode = new JTextField();
        panel.add(zipCode);

        panel.add(new JLabel("Building Type:"));
        buildingType = new JTextField();
        panel.add(buildingType);

        add(panel, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        continueButton = new JButton("Continue");
        backButton = new JButton("Back");

        buttonPanel.add(backButton);
        buttonPanel.add(continueButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        continueButton.addActionListener(e -> continueToPayment());
        backButton.addActionListener(e -> goBackToCart());

        setVisible(true);
    }

    /**
     * Validates all address fields and continues to payment page as long as the user input all the correct
     * information
     */

    private void continueToPayment() {
        // Validate fields
        if (streetName.getText().trim().isEmpty() ||
                cityName.getText().trim().isEmpty() ||
                stateName.getText().trim().isEmpty() ||
                zipCode.getText().trim().isEmpty() ||
                !zipCode.getText().trim().matches("\\d{5}") ||
                buildingType.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please fill out all fields correctly.",
                    "Incomplete Address", JOptionPane.WARNING_MESSAGE);
            return;
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
        new CartUI();
        dispose();
    }
}
