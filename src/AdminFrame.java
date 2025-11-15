import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

//creates the admin frame gui
class AdminFrame extends JFrame implements ActionListener {
    private Container c;
    private JLabel userName;
    private JTextField userNameField;
    private JLabel password;
    private JTextField passwordField;
    private JLabel accountType;
    private JRadioButton customer;
    private JRadioButton driver;
    private JRadioButton restaurant;
    private JButton submit;
    private JButton reset;
    private JLabel res;
    private JTextArea resadd;
    private LoginDAO loginDAO = new LoginDAO();

    public AdminFrame(){

        setTitle("BSU Eats Delivery App Admin Panel");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        userName = new JLabel("Username");
        userName.setFont(new Font("Arial", Font.PLAIN, 20));
        userName.setSize(200, 40);
        userName.setLocation(150, 150);
        c.add(userName);

        userNameField = new JTextField();
        userNameField.setFont(new Font("Arial", Font.PLAIN, 15));
        userNameField.setSize(400, 40);
        userNameField.setLocation(250, 150);
        c.add(userNameField);

        password = new JLabel("Password");
        password.setFont(new Font("Ariel", Font.PLAIN, 20));
        password.setSize(200, 40);
        password.setLocation(150, 250);
        c.add(password);

        passwordField = new JTextField();
        passwordField.setFont(new Font("Ariel", Font.PLAIN, 15));
        passwordField.setSize(400, 40);
        passwordField.setLocation(250, 250);
        c.add(passwordField);

        accountType = new JLabel("Account Type");
        accountType.setFont((new Font("Ariel", Font.PLAIN, 20)));
        accountType.setSize(200, 40);
        accountType.setLocation(375, 300);
        c.add(accountType);

        customer = new JRadioButton("Customer");
        customer.setFont(new Font("Arial", Font.PLAIN, 15));
        customer.setSelected(false);
        customer.setSize(150, 40);
        customer.setLocation(200, 340);
        c.add(customer);

        driver = new JRadioButton("Driver");
        driver.setFont(new Font("Arial", Font.PLAIN, 15));
        driver.setSelected(false);
        driver.setSize(150, 40);
        driver.setLocation(400, 340);
        c.add(driver);

        restaurant = new JRadioButton("Restaurant");
        restaurant.setFont(new Font("Arial", Font.PLAIN, 15));
        restaurant.setSelected(false);
        restaurant.setSize(150, 40);
        restaurant.setLocation(600, 340);
        c.add(restaurant);



        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN, 15));
        submit.setSize(100, 20);
        submit.setLocation(300, 450);
        submit.addActionListener(this);
        c.add(submit);

        reset = new JButton("Reset");
        reset.setFont(new Font("Arial", Font.PLAIN, 15));
        reset.setSize(100, 20);
        reset.setLocation(450, 450);
        reset.addActionListener(this);
        c.add(reset);
        
        res = new JLabel("");
        res.setFont(new Font("Arial", Font.PLAIN, 20));
        res.setSize(500, 25);
        res.setLocation(100, 500);
        c.add(res);

        resadd = new JTextArea();
        resadd.setFont(new Font("Arial", Font.PLAIN, 15));
        resadd.setSize(200, 75);
        resadd.setLocation(580, 175);
        resadd.setLineWrap(true);
        c.add(resadd);

        c.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if (customer.isSelected() && driver.isSelected() || customer.isSelected() && restaurant.isSelected() || driver.isSelected() && restaurant.isSelected()) {
                res.setText("Please select only" +
                        "one account type");
            } else if (!customer.isSelected() && !driver.isSelected() && !restaurant.isSelected()) {
                res.setText("Please select an" +
                        "account type");
            } else if(userNameField.getText().equals("")) {
                res.setText("Please input a" +
                        "username");
            } else if(passwordField.getText().equals("")) {
                res.setText("Please input a" +
                        "Password");
            }else {
                if (customer.isSelected()) {
                    try {
                        loginDAO.insert(userNameField.getText(), passwordField.getText(), 1);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (driver.isSelected()) {
                    try {
                        loginDAO.insert(userNameField.getText(), passwordField.getText(), 2);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if (restaurant.isSelected()) {
                    try {
                        loginDAO.insert(userNameField.getText(), passwordField.getText(), 3);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                String data1;
                String data
                        = "Username : "
                        + userNameField.getText() + "\n"
                        + "Password : "
                        + passwordField.getText() + "\n";
            }
        } else if (e.getSource() == reset) {
            String def = "";
            userNameField.setText(def);
            passwordField.setText(def);
        }

        }
    }




