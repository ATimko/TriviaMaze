package Controller;
import views.gameWindow;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // TriviaMaze triviaMaze = new TriviaMaze();
        // triviaMaze.startGame();
        SwingUtilities.invokeLater(() -> {
            // Create a new GameWindow
            JFrame frame = new gameWindow();
            // Set the default close operation
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // Make the window visible
            frame.setVisible(true);
        });
    }
}