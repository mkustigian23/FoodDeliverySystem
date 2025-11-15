import javax.swing.*;
import java.awt.*;

class Cart extends Menu {
    public Cart(String itemName, double price) {
        super(itemName, price);
    }
}
// Cart UI
public class CartUI extends JFrame {

    private DefaultListModel<Cart> cartModel;
    private JLabel totalLabel;
    private JTextField itemField;
    private JTextField priceField;

    public void CartFrame() {
        setTitle("Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));

        //Cart List
        cartModel = new DefaultListModel<>();
        JList<Cart> cartList = new JList<>(cartModel);
        JScrollPane scrollPane = new JScrollPane(cartList);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Cart Items"));
        add(scrollPane, BorderLayout.CENTER);

        //Total Label
        totalLabel = new JLabel("Total: " + priceField, SwingConstants.CENTER);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(totalLabel, BorderLayout.SOUTH);


        setVisible(true);
    }

    private void updateTotal() {
        double total = 0;
        for (int i = 0; i < cartModel.getSize(); i++) {
            total += cartModel.getElementAt(i).getPrice();
        }
        totalLabel.setText("Total: $" + String.format("%.2f", total));
    }
}