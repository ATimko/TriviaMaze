package views;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class gameWindow extends JFrame {
    public gameWindow() {
        setTitle("Trivia Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1432, 768);  // Appropriately size the window to fit the background

        // Setup the background image
        URL imageUrl = getClass().getResource("Mazebackground.png"); // Adjusted path
        if (imageUrl == null) {
            System.out.println("Image not found");
            return;
        }
        ImageIcon image = new ImageIcon(imageUrl);
        JLabel backgroundLabel = new JLabel(image);
        backgroundLabel.setSize(1432, 768); // Ensure the label completely covers the frame
        setContentPane(backgroundLabel);
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Trivia Maze", SwingConstants.CENTER);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setFont(new Font("Serif", Font.BOLD, 50));
        label.setForeground(Color.BLUE);
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // Make the panel transparent
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create buttons
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton exitButton = new JButton("Exit");

        // Styling buttons
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to the panel
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(loadGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createVerticalGlue());

        // Add the menu
        configureMenu();

        // Add the button panel and title label
        add(buttonPanel, BorderLayout.SOUTH);
        add(label, BorderLayout.NORTH);

        // Ensure the window is visible
        setLocationRelativeTo(null);  // Center on screen
        setVisible(true);
    }

    private void configureMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("File");
        JMenuItem saveGameStatus = new JMenuItem("Save Game");
        JMenuItem loadSavedGame = new JMenuItem("Load Game");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        gameMenu.add(saveGameStatus);
        gameMenu.add(loadSavedGame);
        gameMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        JMenuItem rulesMenuItem = new JMenuItem("Rules");
        helpMenu.add(aboutMenuItem);
        helpMenu.add(rulesMenuItem);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }
}
