import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DriverFrame extends JFrame {
    private JComboBox<String> driverCombo;
    private JComboBox<String> vehicleCombo;
    private JButton selectButton, doneButton;
    private DriverDAO driverDAO;
    private ArrayList<String> driverList;

    public DriverFrame() {
        setTitle("Driver Selection");
        setBounds(300, 90, 700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel driverLabel = new JLabel("Select a Driver:");
        driverCombo = new JComboBox<>();
        JLabel vehicleLabel = new JLabel("Select Vehicle:");
        vehicleCombo = new JComboBox<>();

        selectButton = new JButton("Select Driver");
        doneButton = new JButton("Done");

        add(driverLabel);
        add(driverCombo);
        add(vehicleLabel);
        add(vehicleCombo);
        add(selectButton);
        add(doneButton);

        driverDAO = new DriverDAO();

        // Load drivers from database
        try {
            driverList = new ArrayList<>();
            List<String> allDrivers = driverDAO.getAll(); // returns list of "id: name"
            driverList.addAll(allDrivers);

            for (String d : driverList) {
                driverCombo.addItem(d);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading drivers: " + e.getMessage());
            driverList = new ArrayList<>();
        }

        // When driver changes, load corresponding vehicle
        driverCombo.addActionListener(e -> {
            String selected = (String) driverCombo.getSelectedItem();
            vehicleCombo.removeAllItems();

            if (selected == null) return;

            // Extract driver ID from selected string "id: name"
            int id = Integer.parseInt(selected.split(":")[0].trim());
            try {
                List<String> allDrivers = driverDAO.getAll();
                // Assuming DAO stores vehicles; for simplicity, we'll just match the ID
                for (String driverInfo : allDrivers) {
                    if (driverInfo.startsWith(id + ":")) {
                        // For now, just display the vehicle in combo
                        // If your DAO had vehicle info, you can replace with actual vehicle
                        vehicleCombo.addItem(driverInfo.split(":")[1].trim());
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error loading vehicle: " + ex.getMessage());
            }
        });

        selectButton.addActionListener(e -> {
            String selectedDriver = (String) driverCombo.getSelectedItem();
            String selectedVehicle = (String) vehicleCombo.getSelectedItem();

            if (selectedDriver == null || selectedVehicle == null) {
                JOptionPane.showMessageDialog(this, "Please select a driver and vehicle first");
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Driver selected: " + selectedDriver +
                            "\nVehicle: " + selectedVehicle);
        });

        doneButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Thank you for using the Driver App!");
            dispose();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DriverFrame::new);
    }
}
