package views;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class gameWindow extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public gameWindow() {
        setTitle("Trivia Maze");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 900);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createMainMenuPanel(), "Main Menu");
        mainPanel.add(new roomUI(), "Game");

        setJMenuBar(createMenuBar());

        add(mainPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createMainMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        URL imageUrl = getClass().getResource("Mazebackground.png");
        if (imageUrl == null) {
            System.out.println("Image not found");
            return panel;
        }
        ImageIcon image = new ImageIcon(imageUrl);
        JLabel imageLabel = new JLabel(image);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel titleLabel = new JLabel("Trivia Maze", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 50));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.LIGHT_GRAY);
        titleLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton newGameButton = new JButton("New Game");
        JButton loadGameButton = new JButton("Load Game");
        JButton exitButton = new JButton("Exit");

        Font buttonFont = new Font("Arial", Font.BOLD, 25);
        newGameButton.setFont(buttonFont);
        loadGameButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);

        newGameButton.setFocusable(false);
        loadGameButton.setFocusable(false);
        exitButton.setFocusable(false);

        newGameButton.setMargin(new Insets(20, 50, 20, 50));
        loadGameButton.setMargin(new Insets(20, 50, 20, 50));
        exitButton.setMargin(new Insets(20, 50, 20, 50));

        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        newGameButton.addActionListener(e -> cardLayout.show(mainPanel, "Game"));
        exitButton.addActionListener(e -> System.exit(0));

        buttonPanel.add(Box.createVerticalGlue());
        buttonPanel.add(newGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 20)));
        buttonPanel.add(loadGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 20)));
        buttonPanel.add(exitButton);
        buttonPanel.add(Box.createVerticalGlue());

        panel.add(imagePanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(titleLabel, BorderLayout.NORTH);

        return panel;
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("File");
        JMenuItem saveGameStatus = new JMenuItem("Save Game");
        JMenuItem loadSavedGame = new JMenuItem("Load Game");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(e -> System.exit(0));
        gameMenu.add(saveGameStatus);
        gameMenu.add(loadSavedGame);
        gameMenu.add(exitMenuItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutMenuItem = new JMenuItem("About");
        JMenuItem rulesMenuItem = new JMenuItem("Rules");
        aboutMenuItem.addActionListener(e -> new aboutUI());
        helpMenu.add(aboutMenuItem);
        rulesMenuItem.addActionListener(e -> new ruleUI());
        helpMenu.add(rulesMenuItem);

        menuBar.add(gameMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }
}
