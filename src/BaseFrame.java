import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    protected Container c;

    public BaseFrame(String title, int width, int height) {
        setTitle(title);
        setBounds(900, 900, width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        c = getContentPane();
        c.setLayout(null);

        // Apply background color to ALL pages
        c.setBackground(new Color(230, 240, 255)); // light blue
    }

    public BaseFrame() {
    }
}