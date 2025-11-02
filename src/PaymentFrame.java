import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PaymentFrame extends JFrame implements ActionListener {
    private JTextField firstName, lastName, cardNumber, expiration, cvv, totalAmount;
    private JButton payButton;
    private JLabel statusLabel;
    private Payment payment;

    public PaymentFrame() {
        payment = new Payment();
        payment.setBalance(100);

        setTitle("Payment Information");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(8, 4, 10, 10));

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
        totalAmount = new JTextField();
        add(totalAmount);

        payButton = new JButton("Pay Now");
        payButton.addActionListener(this);
        add(payButton);

        statusLabel = new JLabel("");
        add(statusLabel);

        setVisible(true);
    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {
            try {
                int price = Integer.parseInt(totalAmount.getText());
                payment.purchase(price);
                JOptionPane.showMessageDialog(this, payment.sendReceipt());
                statusLabel.setText("Payment successful!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter all fields", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}



