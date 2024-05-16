package views;

import Maze.room;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class roomUI extends JPanel {

    private Image backgroundImage;
    private room room;

    public roomUI() {
        room = new room();

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

        // Add the door buttons from the Room class
        JButton[] doors = room.getDoors();
        for (int i = 0; i < doors.length; i++) {
            JButton doorButton = doors[i];
            add(doorButton);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);  // Draw the background image
        }
    }
}
