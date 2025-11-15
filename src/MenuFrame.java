import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class MenuFrame extends JFrame {

    private CartUI cartPanel;
    private int restaurantId;
    private String restaurantName;

    public MenuFrame(int restaurantId, String restaurantName, CartUI cartPanel) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.cartPanel = cartPanel;

        setTitle("Menu - " + restaurantName);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // title at top
        JLabel titleLabel = new JLabel("Menu for " + restaurantName, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        add(titleLabel, BorderLayout.NORTH);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.addActionListener(e -> {
            // Open previous frame
            new CustomerFrame();
            dispose();            // closes the current MenuFrame
        });


        // Add the button to the NORTH or SOUTH
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(backButton, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);


        // panel for menu grid
        JPanel mainPanel = new JPanel(new GridLayout(0, 2, 20, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        MenuDAO dao = new MenuDAO();
        List<Menu> menuItems;

        try {
            menuItems = dao.getMenuByRestaurant(restaurantId);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading menu: " + e.getMessage());
            menuItems = List.of();
        }

        // add menu items
        for (Menu item : menuItems) {
            mainPanel.add(createMenuItemPanel(item));
        }

        // cart on the side of the menu
        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(mainPanel),  // menu left
                cartPanel                     // cart right
        );

        splitPane.setDividerLocation(550);
        splitPane.setResizeWeight(0.75);

        add(splitPane, BorderLayout.CENTER);
        setVisible(true);

    }


    private JPanel createMenuItemPanel(Menu item) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.setPreferredSize(new Dimension(200, 200));

        // image
        ImageIcon icon;
        try {
            icon = new ImageIcon(item.getMenu_imagePath());
        } catch (Exception e) {
            icon = new ImageIcon("src/default.png");
        }

        Image img = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        JLabel imgLabel = new JLabel(new ImageIcon(img));

        // name + price
        JLabel nameLabel = new JLabel(item.getName(), JLabel.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel priceLabel = new JLabel(String.format("$%.2f", item.getPrice()), JLabel.CENTER);

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(imgLabel, BorderLayout.CENTER);
        panel.add(priceLabel, BorderLayout.SOUTH);

        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

                Cart cartItem = new Cart(item.getName(), item.getPrice());
                cartPanel.addItemToCart(cartItem);

                JOptionPane.showMessageDialog(MenuFrame.this,
                        item.getName() + " added to cart!");
            }
        });


        return panel;
    }
}
