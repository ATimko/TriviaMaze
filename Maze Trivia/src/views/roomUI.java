package views;

import javax.swing.*;
import java.awt.*;

public class roomUI extends JPanel {

    public roomUI() {
        setLayout(new BorderLayout());

        JLabel gameLabel = new JLabel("Game Panel", SwingConstants.CENTER);
        gameLabel.setFont(new Font("Serif", Font.BOLD, 50));
        add(gameLabel, BorderLayout.CENTER);

        // Add more components and game logic here
    }
}
