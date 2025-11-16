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

        this.total = totalFromCart;  // save the total
        this.address = address;


        payment = new Payment(total); // pass total into payment

        setTitle("Payment Information");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2, 10, 10));

        add(new JLabel("First Name:"));
        firstName = new JTextField();
        add(firstName);

        add(new JLabel("Last Name:"));
        lastName = new JTextField();
        add(lastName);

        add(new JLabel("Card Number:"));
        cardNumber = new JTextField();
        add(cardNumber);

        add(new JLabel("Expiration Date (MM/YY):"));
        expiration = new JTextField();
        add(expiration);

        add(new JLabel("CVV:"));
        cvv = new JTextField();
        add(cvv);

        add(new JLabel("Amount:"));
        totalAmount = new JTextField(String.valueOf(total));
        totalAmount.setEditable(false);  // amount should not be editable
        add(totalAmount);

        payButton = new JButton("Pay Now");
        payButton.addActionListener(this);
        add(payButton);

        statusLabel = new JLabel("");
        add(statusLabel);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> {
            new AddressFrame(); // opens the previous frame
            dispose();            // closes the current MenuFrame
        });
        add(backButton);   // Adds the back button

        setVisible(true);

    }

    /**
     * Handles the action for the Pay Now button
     * Makes sure all fields for payment information are entered (name, card number, expiration date, cvv)
     * @param e the ActionEvent becomes triggered when the user clicks the Pay Now button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {

            // Makes sure the user enters their first name
            if (firstName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your first name.");
                return;
            }

            // Makes sure the user enters their last name
            if(lastName.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your last name.");
                return;
            }

            // Makes sure the user enters their card number
            String card = cardNumber.getText().trim();
            if (!card.matches("\\d{16}")) {
                JOptionPane.showMessageDialog(this, "Card number must be 16 digits.");
                return;
            }

            // Makes sure the user enters their expiration date
            String exp = expiration.getText().trim();
            if (!exp.matches("(0[1-9]|1[0-2])\\/\\d{2}")) {
                JOptionPane.showMessageDialog(this, "Expiration must be in MM/YY format.");
                return;
            }

            // Makes sure the user enters their cvv number
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



