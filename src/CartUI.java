import javax.swing.*;
import java.awt.*;

// Cart UI
public class CartUI extends JPanel {

    private DefaultListModel<Cart> cartModel;
    private JLabel totalLabel;
    private JTextField itemField;
    private JTextField priceField;
    private JList<Cart> cartList;

    public CartUI() {

        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));

        //Cart List
        cartModel = new DefaultListModel<>();
        cartList = new JList<>(cartModel);
        JScrollPane scrollPane = new JScrollPane(cartList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Cart Items"));
        add(scrollPane, BorderLayout.CENTER);

        //Total Label
        totalLabel = new JLabel("Total: " + priceField, SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(totalLabel, BorderLayout.SOUTH);

        // Remove button
        JButton removeButton = new JButton("Remove Item");
        removeButton.addActionListener(e -> removeItem());
        add(removeButton, BorderLayout.NORTH);

        setVisible(true);
    }

    private void removeItem() {
        int selectedIndex = cartList.getSelectedIndex();
        if (selectedIndex != -1) {
            cartModel.remove(selectedIndex);
            updateTotal();
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to remove.");
        }
    }

        public void addItemToCart (Cart item){
            cartModel.addElement(item);
            updateTotal();
        }

        private void updateTotal () {
            double total = 0;
            for (int i = 0; i < cartModel.getSize(); i++) {
                total += cartModel.getElementAt(i).getPrice();
            }
            totalLabel.setText("Total: $" + String.format("%.2f", total));
        }
    }
