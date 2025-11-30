/**
 * Documentation: Payment Frame
 *
 * This class is the UI for the payment page for entering payment information in order for the user to
 * pay for their food.
 *
 * Module Purpose:
 * Collects user payment information like their name, card number, expiration date, and cvv number.
 * Displays the total amount of all the food they want to order passed from the CartUI
 * Makes sure the user input all fields otherwise the payment will not process successfully
 * Interacts with payment class to simulate a purchase and receipt
 *
 * Key Methods:
 * PaymentFrame(int totalFromCart, Address address):
 * - Constructor sets up the payment window
 * - Stores the total, creates labels, text fields, and action buttons. It also initializes GUI components.
 * actionPerformed(ActionEvent e):
 * - Happens when the Pay Now button is clicked and makes sure there are inputs in all the fields.
 * - Checks if the first name, last name, cvv, card number, and expiration date are filled in correctly.
 * - If valid it calls payment.purchase() and displays receipt.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentFrame extends JFrame implements ActionListener {
    private JTextField firstName;
    private JTextField lastName;
    private JTextField cardNumber;
    private JTextField expiration;
    private JTextField cvv;
    private JTextField totalAmount;

    private JButton payButton;
    private JButton backButton;
    private JLabel statusLabel;
    private Payment payment;
    private Address address;

    private int total; // <-- stores the total passed in

    /**
     * Creates the payment window where user will enter their payment info
     *
     * @param totalFromCart the total price of all the food items that the user added to their cart, passed from CartUI
     *                      and displayed here
     * @param address the Address object containing the delivery information for the user
     */
    public PaymentFrame(int totalFromCart, Address address) {

        this.total = totalFromCart;
        this.address = address;
        payment = new Payment(total);

        setTitle("Payment Information");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel with background color
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(173, 216, 230)); // light blue
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Enter Payment Details", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formPanel.setOpaque(false); // keep background consistent

        Font labelFont = new Font("Arial", Font.PLAIN, 16);

        formPanel.add(new JLabel("First Name:")).setFont(labelFont);
        firstName = new JTextField();
        formPanel.add(firstName);

        formPanel.add(new JLabel("Last Name:")).setFont(labelFont);
        lastName = new JTextField();
        formPanel.add(lastName);

        formPanel.add(new JLabel("Card Number:")).setFont(labelFont);
        cardNumber = new JTextField();
        formPanel.add(cardNumber);

        formPanel.add(new JLabel("Expiration (MM/YY):")).setFont(labelFont);
        expiration = new JTextField();
        formPanel.add(expiration);

        formPanel.add(new JLabel("CVV:")).setFont(labelFont);
        cvv = new JTextField();
        formPanel.add(cvv);

        formPanel.add(new JLabel("Amount:")).setFont(labelFont);
        totalAmount = new JTextField(String.valueOf(total));
        totalAmount.setEditable(false);
        formPanel.add(totalAmount);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setOpaque(false);

        payButton = new JButton("Pay Now");
        payButton.setFont(new Font("Arial", Font.BOLD, 16));
        payButton.addActionListener(this);
        buttonPanel.add(payButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 16));
        backButton.addActionListener(e -> {
            new AddressFrame();
            dispose();
        });
        buttonPanel.add(backButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

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

        // Status label
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        mainPanel.add(statusLabel, BorderLayout.NORTH);

        add(mainPanel);
        add(logoutButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Handles the action for the Pay Now button
     * Makes sure all fields for payment information are entered (name, card number, expiration date, cvv)
     * @param e the ActionEvent becomes triggered when the user clicks the Pay Now button
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {
            if (firstName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your first name.");
                return;
            }
            if (lastName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your last name.");
                return;
            }
            String card = cardNumber.getText().trim();
            if (!card.matches("\\d{16}")) {
                JOptionPane.showMessageDialog(this, "Card number must be 16 digits.");
                return;
            }
            String exp = expiration.getText().trim();
            if (!exp.matches("(0[1-9]|1[0-2])\\/\\d{2}")) {
                JOptionPane.showMessageDialog(this, "Expiration must be in MM/YY format.");
                return;
            }
            String cvvText = cvv.getText().trim();
            if (!cvvText.matches("\\d{3,4}")) {
                JOptionPane.showMessageDialog(this, "CVV must be 3 or 4 digits.");
                return;
            }

            payment.purchase();
            JOptionPane.showMessageDialog(this, payment.sendReceipt());
            statusLabel.setText("Payment successful!");
        }
    }
}




