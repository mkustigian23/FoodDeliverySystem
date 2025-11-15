import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.sql.SQLException;

public class CustomerFrame extends JFrame {

    private CartUI cartPanel = new CartUI();

    public CustomerFrame() {

        setTitle("Select a Restaurant");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("Select a Restaurant", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        RestaurantDAO dao = new RestaurantDAO();
        List<Restaurant> restaurants;
        try {
            restaurants = dao.getAll(); // fetch from database
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading restaurants: " + e.getMessage());
            restaurants = List.of(); // empty list if error
        }


        for (Restaurant r : restaurants) {
            JPanel panel = createRestaurantPanel(r);
            mainPanel.add(panel);
        }

        add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createRestaurantPanel(Restaurant restaurant) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(100, 100));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JLabel nameLabel = new JLabel(restaurant.getName(), JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Load image
        ImageIcon icon = new ImageIcon(restaurant.getImagePath());
        Image img = icon.getImage().getScaledInstance(300, 250, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(img));

        panel.add(picLabel, BorderLayout.CENTER);
        panel.add(nameLabel, BorderLayout.SOUTH);

        // Make panel clickable
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Single click action
                new MenuFrame(restaurant.getId(), restaurant.getName());
                dispose(); // close CustomerFrame if needed
            }
        });


        return panel;
    }

}
