/**
 * DocumentationL LoginFrame
 *
 * This class provides the UI for login and registration and users can enter their username, password,
 * gender, date of birth, address, and accept terms.
 * Upon submission, the login is authenticated and based on the user account type they will be redirected.
 *
 * Module Purpose:
 * - Collects login and user information for registration.
 * - Provides GUI components for text input, radio buttons, combo boxes, and check boxes.
 * - Redirects user to the appropriate frame when they login successfully.
 *
 * Key Methods:
 * LoginFrame():
 * - Constructs the login/register UI with required fields and buttons
 *
 * actionPerformed(ActionEvent e): Handles button clicks for reset and submit. Validates login and redirects users.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class LoginFrame extends JFrame implements ActionListener {
    // methods of the Frame
    private Container c;
    private JLabel title;
    private JLabel userName;
    private JTextField userNameField;
    private JLabel password;
    private JTextField passwordField;
    private JLabel gender;
    private JRadioButton male;
    private JRadioButton female;
    private ButtonGroup gengp;
    private JLabel dob;
    private JComboBox<String> date;
    private JComboBox<String> month;
    private JComboBox<String> year;
    private JCheckBox terms;
    private JButton submit;
    private JButton reset;

    private String dates[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private String months[]
            = { "Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec" };
    private String years[]
            = { "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019" };

    /**
     * Constructs the LoginFrame
     * Initializes the GUI with all fields, label, buttons, and default values.
     * Sets up the layout and action listeners for submit and reset buttons
     */
    public LoginFrame() {
        setTitle("BSU Eats Delivery App");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        c.setBackground(new Color(173, 216, 230)); // light blue

        try {
            ImageIcon icon = new ImageIcon("src/bear-logo.png");
            Image img = icon.getImage().getScaledInstance(350, 250, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            imgLabel.setBounds(600, 200, 200, 150);
            c.add(imgLabel);
        } catch (Exception e) {
            System.out.println("Image not found: " + e.getMessage());
        }

        title = new JLabel("Login / Register");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 40);
        title.setLocation(300, 30);
        c.add(title);

        userName = new JLabel("Username:");
        userName.setFont(new Font("Arial", Font.PLAIN, 20));
        userName.setSize(120, 25);
        userName.setLocation(100, 100);
        c.add(userName);

        userNameField = new JTextField();
        userNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        userNameField.setSize(200, 25);
        userNameField.setLocation(230, 100);
        userNameField.setName("userNameField");
        c.add(userNameField);

        password = new JLabel("Password:");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(120, 25);
        password.setLocation(100, 150);
        c.add(password);

        passwordField = new JTextField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setSize(200, 25);
        passwordField.setLocation(230, 150);
        passwordField.setName("passwordField");
        c.add(passwordField);

        gender = new JLabel("Gender:");
        gender.setFont(new Font("Arial", Font.PLAIN, 20));
        gender.setSize(120, 25);
        gender.setLocation(100, 200);
        c.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN, 16));
        male.setSelected(true);
        male.setSize(80, 25);
        male.setLocation(230, 200);
        male.setName("Male");
        c.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN, 16));
        female.setSelected(false);
        female.setSize(100, 25);
        female.setLocation(320, 200);
        female.setName("Female");
        c.add(female);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        dob = new JLabel("DOB:");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(120, 25);
        dob.setLocation(100, 250);
        c.add(dob);

        date = new JComboBox<>(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(70, 30);
        date.setLocation(230, 250);
        date.setName("date");
        c.add(date);

        month = new JComboBox<>(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(90, 30);
        month.setLocation(310, 250);
        month.setName("month");
        c.add(month);

        year = new JComboBox<>(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(100, 30);
        year.setLocation(410, 250);
        year.setName("year");
        c.add(year);

        terms = new JCheckBox("Accept Terms and Conditions");
        terms.setFont(new Font("Arial", Font.PLAIN, 15));
        terms.setSize(250, 25);
        terms.setLocation(150, 350);
        terms.setName("terms");
        c.add(terms);

        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.setSize(120, 30);
        submit.setLocation(150, 400);
        submit.setName("Submit");
        submit.addActionListener(this);
        c.add(submit);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 16));
        reset.setSize(120, 30);
        reset.setLocation(300, 400);
        reset.setName("Reset");
        reset.addActionListener(this);
        c.add(reset);

        c.setVisible(true);


    }

    /**
     * Handles button actions for submit and reset.
     * Checks the login information using authenticator and redirects the user based on their account type.
     *
     * @param e the ActionEvent triggered by a button click
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset) {
            userNameField.setText("");
            passwordField.setText("");
            male.setSelected(true);
            female.setSelected(false);
            date.setSelectedIndex(0);
            month.setSelectedIndex(0);
            year.setSelectedIndex(0);
            terms.setSelected(false);
            return;
        }

        String username = userNameField.getText();
        String password = passwordField.getText();

        if (!terms.isSelected()) {
            SwingUtilities.invokeLater(() ->
            JOptionPane.showMessageDialog(this, "Please check accept terms and conditions.")
            );
            return;
        }

        if (username.isEmpty() || password.isEmpty()) {
            SwingUtilities.invokeLater(() ->
            JOptionPane.showMessageDialog(this, "Invalid username or password")
            );
            return;
        }

        Integer accountType = Authenticator.checkLogin(username, password);

        if (accountType != null) {
            SwingUtilities.invokeLater(() ->
            JOptionPane.showMessageDialog(this, "Login successful!")
            );

            // close login window
            this.dispose();

            switch (accountType) {
                case 0 -> {
                    AdminFrame a = new AdminFrame();
                    a.setVisible(true);
                }
                case 1 -> {
                    CustomerFrame c = new CustomerFrame();
                    c.setVisible(true);
                }
                case 2 -> {
                    DriverFrame d = new DriverFrame();
                    d.setVisible(true);
                }
                case 3 -> {
                    //RestaurantFrame r = new CustomerFrame();
                    //r.setVisible(true);
                }
            }
            return; // EXIT HERE so your registration code does not run after login
        } else {
            SwingUtilities.invokeLater(() ->
            JOptionPane.showMessageDialog(this, "Invalid username or password")
            );
        }

    }
}