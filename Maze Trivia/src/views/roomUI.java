package views;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class roomUI extends JPanel {

    private Image backgroundImage;

    public roomUI() {
        // Load the image
        URL imageUrl = getClass().getClassLoader().getResource("views/fiveDoors.png");
        if (imageUrl == null) {
            System.out.println("Image not found");
        } else {
            ImageIcon originalImage = new ImageIcon(imageUrl);
            // Resize the image to 1100x800
            backgroundImage = originalImage.getImage().getScaledInstance(1100, 800, Image.SCALE_SMOOTH);
        }

        setLayout(null); // Use absolute positioning for custom layout

        // Create invisible buttons and position them over the doors
        JButton door1Button = createInvisibleButton(10, 150, 150, 310); // Adjust the coordinates and size
        JButton door2Button = createInvisibleButton(260, 150, 150, 310); // Adjust the coordinates and size
        JButton door3Button = createInvisibleButton(515, 150, 150, 310); // Adjust the coordinates and size
        JButton door4Button = createInvisibleButton(760, 150, 150, 310); // Adjust the coordinates and size
        JButton door5Button = createInvisibleButton(1012, 150, 150, 310); // Adjust the coordinates and size

        add(door1Button);
        add(door2Button);
        add(door3Button);
        add(door4Button);
        add(door5Button);
    }

    private JButton createInvisibleButton(int x, int y, int width, int height) {
        JButton button = new JButton();
        button.setBounds(x, y, width, height);
        button.setContentAreaFilled(false); // Make the button invisible
        button.setBorderPainted(false); // Remove the border
        button.setFocusPainted(true); // Remove the focus outline
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);  // Draw the background image
        }
    }
}
