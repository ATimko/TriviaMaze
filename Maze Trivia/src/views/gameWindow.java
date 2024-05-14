package views;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class gameWindow extends JFrame {
    public gameWindow() {
        setTitle("Trivia Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);
        setResizable(false);

        // Setup the background image
        URL imageUrl = getClass().getResource("Mazebackground.png"); // Adjusted path to the uploaded image
        if (imageUrl == null) {
            System.out.println("Image not found");
            return;
        }
        ImageIcon image = new ImageIcon(imageUrl);
        JLabel imageLabel = new JLabel(image);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Create a panel to hold the image and add padding
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0)); // Padding to move the image higher

        // Create and style the title label
        JLabel titleLabel = new JLabel("Trivia Maze", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.LIGHT_GRAY);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Create buttons with improved styling
        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton exitButton = new JButton("Exit");

        Font buttonFont = new Font("Arial", Font.BOLD, 25);
        newGameButton.setFont(buttonFont);
        loadGameButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        // Remove focus rectangle
        newGameButton.setFocusable(false);
        loadGameButton.setFocusable(false);
        exitButton.setFocusable(false);

        // Add margins and borders to the buttons
        newGameButton.setMargin(new Insets(20, 50, 20, 50));
        loadGameButton.setMargin(new Insets(20, 50, 20, 50));
        exitButton.setMargin(new Insets(20, 50, 20, 50));

        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add buttons to the panel
        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 20)));
        buttonPanel.add(loadGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 20)));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createVerticalGlue());

        // Add the menu
        configureMenu();

        // Add the image panel, button panel, and title label
        add(imagePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(titleLabel, BorderLayout.NORTH);

        // Ensure the window is visible
        setLocationRelativeTo(null);
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
