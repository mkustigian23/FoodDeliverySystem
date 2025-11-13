import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class AdminFrame extends JFrame implements ActionListener {
    private Container c;
    private JLabel title;
    private JLabel userName;
    private JTextField userNameField;
    private JLabel password;
    private JTextField passwordField;
    private JLabel phoneNum;
    private JTextField phoneNumField;
    private JLabel gender;
    private JRadioButton male;
    private JRadioButton female;
    private JLabel accountType;
    private JRadioButton customer;
    private JRadioButton driver;
    private JRadioButton restaurant;
    private ButtonGroup gengp;
    private JLabel dob;
    private JComboBox date;
    private JComboBox month;
    private JComboBox year;
    private JLabel addrss;
    private JTextArea addrssArea;
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

                }
                if (driver.isSelected()) {

                }
                if (restaurant.isSelected()) {

                }
                String data1;
                String data
                        = "Username : "
                        + userNameField.getText() + "\n"
                        + "Password : "
                        + passwordField.getText() + "\n";
//                if (male.isSelected())
//                    data1 = "Gender : Male"
//                            + "\n";
//                else
//                    data1 = "Gender : Female"
//                            + "\n";
//                String data2
//                        = "DOB : "
//                        + (String) date.getSelectedItem()
//                        + "/" + (String) month.getSelectedItem()
//                        + "/" + (String) year.getSelectedItem()
//                        + "\n";
//
//                String data3 = "Address : " + addrssArea.getText();
//                tout.setText(data + data1 + data2 + data3);
//                tout.setEditable(false);
//                res.setText("Login Successful..");
//            } else {
//                tout.setText("");
//                resadd.setText("");
//                res.setText("Please accept the"
//                        + " terms & conditions..");
//            }
            }
        } else if (e.getSource() == reset) {
            String def = "";
            userNameField.setText(def);
            passwordField.setText(def);
//            addrssArea.setText(def);
//            phoneNumField.setText(def);
//            res.setText(def);
//            tout.setText(def);
//            terms.setSelected(false);
//            date.setSelectedIndex(0);
//            month.setSelectedIndex(0);
//            year.setSelectedIndex(0);
//            resadd.setText(def);
        }

//        if (terms.isSelected()) {
//            // existing code that shows login details
//            res.setText("Login Successful..");
//
//            RestaurantFrame restaurant = new RestaurantFrame();
//            restaurant.setVisible(true);
//
//            // close the login window
//            this.dispose();
        }
    }




