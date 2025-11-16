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

    private int total; // <-- stores the total passed in


    public PaymentFrame(int totalFromCart) {

        this.total = totalFromCart;  // save the total


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
            // Open previous frame
            new CartUI(); // opens the previous page
            dispose();            // closes the current MenuFrame
        });
        add(backButton);   // Adds the back button

        setVisible(true);

    }

    // method actionPerformed()
    // to get the action performed
    // by the user and act accordingly
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {

            payment.purchase(); // no need to pass a number — we already know the amount

            JOptionPane.showMessageDialog(this, payment.sendReceipt());
            statusLabel.setText("Payment successful!");
        }
    }
}



