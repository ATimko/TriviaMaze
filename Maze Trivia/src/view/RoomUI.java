package view;

import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomUI extends JPanel {
    private final JLabel roomNumberLabel;
    private final JLabel questionLabel;
    private final JButton button1;
    private final JButton button2;
    private final JButton button3;
    private final JButton button4;
    private final JTextField shortAnswerField;
    private boolean isShortAnswer;
    private final Maze maze;
    private final JButton upButton, downButton, leftButton, rightButton;

    public RoomUI(Maze maze) {
        this.maze = maze;

        // Initialize components with smaller font
        Font largeFont = new Font("Arial", Font.BOLD, 24); // Smaller font for room number and question
        Font gridFont = new Font("Arial", Font.BOLD, 24); // Smaller font for grid numbers
        Font buttonFont = new Font("Arial", Font.BOLD, 14); // Font for buttons

        // Custom Colors
        Color backgroundColor = new Color(60, 63, 65);
        Color textColor = new Color(187, 187, 187);
        Color buttonColor = new Color(75, 110, 175);
        Color buttonTextColor = new Color(255, 255, 255);

        // Set background color
        setBackground(backgroundColor);

        roomNumberLabel = new JLabel();
        roomNumberLabel.setFont(largeFont);
        roomNumberLabel.setForeground(textColor);
        updateRoomNumber();

        questionLabel = new JLabel();
        questionLabel.setFont(largeFont);
        questionLabel.setForeground(textColor);
        displayQuestion();

        JPanel gridPanel = new JPanel(new GridLayout(5, 5, 3, 3)); // Create a 5x5 grid panel with smaller gaps
        gridPanel.setPreferredSize(new Dimension(500, 500)); // Smaller size for the grid panel
        gridPanel.setMinimumSize(new Dimension(300, 300));

        for (int i = 0; i < 25; i++) {
            JPanel roomPanel = new JPanel(new BorderLayout());
            roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            roomPanel.setBackground(Color.WHITE);
            roomPanel.setPreferredSize(new Dimension(60, 60)); // Smaller size for each cell
            gridPanel.add(roomPanel);

            JLabel numberLabel = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
            numberLabel.setFont(gridFont);
            roomPanel.add(numberLabel, BorderLayout.CENTER);
        }

        // Arrow Buttons Panel
        JPanel arrowPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        Dimension arrowButtonSize = new Dimension(60, 60); // Square dimensions for arrow buttons

        upButton = createArrowButton("↑", arrowButtonSize);
        downButton = createArrowButton("↓", arrowButtonSize);
        leftButton = createArrowButton("←", arrowButtonSize);
        rightButton = createArrowButton("→", arrowButtonSize);

        gbc.gridx = 1;
        gbc.gridy = 0;
        arrowPanel.add(upButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        arrowPanel.add(leftButton, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        arrowPanel.add(rightButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        arrowPanel.add(downButton, gbc);

        // Create buttons explicitly
        button1 = new JButton("maze.getAnswerChoices(0)");
        button2 = new JButton("maze.getAnswerChoices(1)");
        button3 = new JButton("maze.getAnswerChoices(2)");
        button4 = new JButton("maze.getAnswerChoices(3)");

        // Set button properties
        JButton[] buttons = {button1, button2, button3, button4};
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setBackground(buttonColor);
            button.setForeground(buttonTextColor);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            button.setFocusPainted(false); // Disable the focus border
            Dimension buttonSize = new Dimension(200, 50); // Smaller size for buttons
            button.setPreferredSize(buttonSize);
            button.setMinimumSize(buttonSize); // Force the minimum size to be the same as preferred size
            button.setMaximumSize(buttonSize); // Set the maximum size to ensure height doesn't change
        }

        shortAnswerField = new JTextField();
        shortAnswerField.setFont(buttonFont);
        shortAnswerField.setPreferredSize(new Dimension(300, 30)); // Smaller size for text field
        shortAnswerField.setMinimumSize(new Dimension(300, 30));
        shortAnswerField.setVisible(false); // Initially hidden

        // Set up GroupLayout
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        // Create horizontal group
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(roomNumberLabel))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(questionLabel))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(gridPanel)
                                .addGap(10)
                                .addComponent(arrowPanel))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(button1)
                                .addGap(5)
                                .addComponent(button2)
                                .addGap(10)
                                .addComponent(button3)
                                .addGap(5)
                                .addComponent(button4))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10)
                                .addComponent(shortAnswerField)
                                .addGap(10))
        );

        // Create vertical group
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(10)
                        .addComponent(roomNumberLabel)
                        .addGap(10)
                        .addComponent(questionLabel)
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(gridPanel)
                                .addComponent(arrowPanel))
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(button1)
                                .addComponent(button2)
                                .addComponent(button3)
                                .addComponent(button4))
                        .addGap(10)
                        .addComponent(shortAnswerField)
                        .addGap(10)
        );

        // Initialize navigation buttons state
        updateNavigationButtons();
    }

    private JButton createArrowButton(String text, Dimension size) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        button.setFocusPainted(false); // Disable the focus border
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.equals("↑")) {
                    maze.moveUp();
                } else if (text.equals("↓")) {
                    maze.moveDown();
                } else if (text.equals("←")) {
                    maze.moveLeft();
                } else if (text.equals("→")) {
                    maze.moveRight();
                }
                updateRoomNumber();
                updateNavigationButtons();
                displayQuestion();
            }
        });
        return button;
    }

    public void updateRoomNumber() {
        int currentRoomNumber = maze.getCurrentRoomNumber();
        roomNumberLabel.setText("Room " + currentRoomNumber);
    }

    public void displayQuestion() {
        String questionText = maze.getQuestionText();
        if (questionText == null) {
            questionLabel.setText("Question:");
        } else {
            questionLabel.setText("Question: " + questionText);
        }
    }

    public void updateQuestionUI(String questionType, String questionText, String[] choices) {
        questionLabel.setText(questionText);

        switch (questionType) {
            case "multiple_choice" -> {
                isShortAnswer = false;
                shortAnswerField.setVisible(false);
                button1.setVisible(true);
                button2.setVisible(true);
                button3.setVisible(true);
                button4.setVisible(true);
                button1.setText(choices.length > 0 ? choices[0] : "");
                button2.setText(choices.length > 1 ? choices[1] : "");
                button3.setText(choices.length > 2 ? choices[2] : "");
                button4.setText(choices.length > 3 ? choices[3] : "");
            }
            case "short_answer" -> {
                isShortAnswer = true;
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                shortAnswerField.setVisible(true);
                shortAnswerField.setText("");
            }
            case "true_false" -> {
                isShortAnswer = false;
                shortAnswerField.setVisible(false);
                button1.setVisible(true);
                button2.setVisible(true);
                button3.setVisible(false);
                button4.setVisible(false);
                button1.setText("True");
                button2.setText("False");
            }
        }
        revalidate();
        repaint();
    }

    public void updateQuestionUI(String questionType, String questionText) {
        updateQuestionUI(questionType, questionText, new String[4]);
    }

    private void updateNavigationButtons() {
        boolean canMoveUp = maze.canMoveUp();
        boolean canMoveDown = maze.canMoveDown();
        boolean canMoveLeft = maze.canMoveLeft();
        boolean canMoveRight = maze.canMoveRight();

        upButton.setEnabled(canMoveUp);
        downButton.setEnabled(canMoveDown);
        leftButton.setEnabled(canMoveLeft);
        rightButton.setEnabled(canMoveRight);
    }
}
