import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class CartUI extends JFrame {

    private JPanel itemsPanel;
    private JLabel totalPriceLabel;

    public CartUI() {
        setTitle("Your Cart");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Your Cart", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // Scrollable items panel
        itemsPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        add(new JScrollPane(itemsPanel), BorderLayout.CENTER);

        // Bottom section with total and buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());

        totalPriceLabel = new JLabel("Total: $" + String.format("%.2f", CartDAO.getTotal()));
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        totalPriceLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        bottomPanel.add(totalPriceLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            new MenuFrame(0, "Menu"); // replace with real restaurant data
            dispose();
        });

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Checkout not implemented yet!");
        });

        buttonPanel.add(backButton);
        buttonPanel.add(checkoutButton);

        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);

        refreshCartDisplay();

        setVisible(true);
    }

    private void refreshCartDisplay() {
        itemsPanel.removeAll();

        List<Menu> items = CartDAO.getItems();

        if (items.isEmpty()) {
            itemsPanel.add(new JLabel("Your cart is empty.", JLabel.CENTER));
        } else {
            for (Menu item : items) {
                itemsPanel.add(createCartItemRow(item));
            }
        }

        totalPriceLabel.setText("Total: $" + String.format("%.2f", CartDAO.getTotal()));

        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    private JPanel createCartItemRow(Menu item) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        row.setPreferredSize(new Dimension(400, 50));

        JLabel nameLabel = new JLabel(item.getName());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        row.add(nameLabel, BorderLayout.WEST);

        JLabel priceLabel = new JLabel("$" + String.format("%.2f", item.getPrice()));
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        row.add(priceLabel, BorderLayout.CENTER);

        JButton removeBtn = new JButton("Remove");
        removeBtn.addActionListener((ActionEvent e) -> {
            CartDAO.removeItem(item);
            refreshCartDisplay();
        });

        row.add(removeBtn, BorderLayout.EAST);

        return row;
    }
}