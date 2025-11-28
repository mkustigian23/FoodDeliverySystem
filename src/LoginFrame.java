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
import java.util.Arrays;

class LoginFrame extends BaseFrame implements ActionListener {
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
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;
    private JCheckBox terms;
    private JButton submit;
    private JButton reset;
    private JTextArea tout;
    private JLabel res;
    private JTextArea resadd;

    private String dates[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private String months[]
            = { "Jan", "feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec" };
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
    public LoginFrame()
    {
        setTitle("BSU Eats Delivery App");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        try {
            ImageIcon icon = new ImageIcon("src/bearlogo.png");
            Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
            JLabel imgLabel = new JLabel(new ImageIcon(img));
            imgLabel.setBounds(450, 200, 200, 150);
            c.add(imgLabel);
        } catch (Exception e) {
            System.out.println("Image not found: " + e.getMessage());
        }

        title = new JLabel("Login/Register");
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(300, 40);
        title.setLocation(300, 40);
        c.add(title);

        userName = new JLabel("Username");
        userName.setFont(new Font("Arial", Font.PLAIN, 20));
        userName.setSize(100, 20);
        userName.setLocation(100, 100);
        c.add(userName);

        userNameField = new JTextField();
        userNameField.setFont(new Font("Arial", Font.PLAIN, 15));
        userNameField.setSize(190, 20);
        userNameField.setLocation(200, 100);
        c.add(userNameField);

        password = new JLabel("Password");
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setSize(100, 20);
        password.setLocation(100, 150);
        c.add(password);

        passwordField = new JTextField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordField.setSize(150, 20);
        passwordField.setLocation(200, 150);
        c.add(passwordField);

        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.PLAIN, 20));
        gender.setSize(100, 20);
        gender.setLocation(100, 200);
        c.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN, 15));
        male.setSelected(true);
        male.setSize(75, 20);
        male.setLocation(200, 200);
        c.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN, 15));
        female.setSelected(false);
        female.setSize(80, 20);
        female.setLocation(275, 200);
        c.add(female);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        dob = new JLabel("DOB");
        dob.setFont(new Font("Arial", Font.PLAIN, 20));
        dob.setSize(100, 20);
        dob.setLocation(100, 250);
        c.add(dob);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(70, 30);
        date.setLocation(200, 250);
        c.add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(80, 30);
        month.setLocation(250, 250);
        c.add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(90, 30);
        year.setLocation(320, 250);
        c.add(year);

        terms = new JCheckBox("Accept Terms And Conditions.");
        terms.setFont(new Font("Arial", Font.PLAIN, 15));
        terms.setSize(250, 20);
        terms.setLocation(150, 400);
        c.add(terms);

        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN, 15));
        submit.setSize(100, 20);
        submit.setLocation(150, 450);
        submit.addActionListener(this);
        c.add(submit);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(270, 450);
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

        String username = userNameField.getText();
        String password = passwordField.getText();

        // ==========================
        //     LOGIN REDIRECTION
        // ==========================
        Integer accountType = Authenticator.checkLogin(username, password);

        if (accountType != null) {

            JOptionPane.showMessageDialog(null, "Login successful!");

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
        }
        else {
            JOptionPane.showMessageDialog(null, "Invalid username or password");
        }

    }
}