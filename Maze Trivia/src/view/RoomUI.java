package view;

import model.Maze;
import model.Question;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * RoomUI class represents the user interface for the rooms in the Trivia Maze game.
 * It handles displaying the current room, the question, and the navigation buttons.
 */
public class RoomUI extends JPanel {
    private final JLabel roomNumberLabel;
    private final JLabel questionLabel;
    private final JButton button1;
    private final JButton button2;
    private final JButton button3;
    private final JButton button4;
    private final JTextField shortAnswerField;
    private final Maze maze;
    private final JButton upButton, downButton, leftButton, rightButton;
    private final GameWindow gameWindow;

    private String pendingDirection = null;

    /**
     * Constructor for the RoomUI class.
     *
     * @param maze       The maze object.
     * @param gameWindow The game window object.
     */
    public RoomUI(Maze maze, GameWindow gameWindow) {
        this.maze = maze;
        this.gameWindow = gameWindow;

        // Initialize components with smaller font
        Font largeFont = new Font("Arial", Font.BOLD, 24);
        Font gridFont = new Font("Arial", Font.BOLD, 24);
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

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

        JPanel gridPanel = new JPanel(new GridLayout(5, 5, 3, 3));
        gridPanel.setPreferredSize(new Dimension(500, 500));
        gridPanel.setMinimumSize(new Dimension(300, 300));

        for (int i = 0; i < 25; i++) {
            JPanel roomPanel = new JPanel(new BorderLayout());
            roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            roomPanel.setBackground(Color.WHITE);
            roomPanel.setPreferredSize(new Dimension(60, 60));
            gridPanel.add(roomPanel);

            JLabel numberLabel = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
            numberLabel.setFont(gridFont);
            roomPanel.add(numberLabel, BorderLayout.CENTER);
        }

        // Arrow Buttons Panel
        JPanel arrowPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        Dimension arrowButtonSize = new Dimension(60, 60);

        upButton = createArrowButton("↑", "UP", arrowButtonSize);
        downButton = createArrowButton("↓", "DOWN", arrowButtonSize);
        leftButton = createArrowButton("←", "LEFT", arrowButtonSize);
        rightButton = createArrowButton("→", "RIGHT", arrowButtonSize);

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
        button1 = new JButton("");
        button2 = new JButton("");
        button3 = new JButton("");
        button4 = new JButton("");

        // Add action listeners for multiple choice buttons
        button1.addActionListener(e -> handleAnswer(button1.getText()));
        button2.addActionListener(e -> handleAnswer(button2.getText()));
        button3.addActionListener(e -> handleAnswer(button3.getText()));
        button4.addActionListener(e -> handleAnswer(button4.getText()));

        // Set button properties
        JButton[] buttons = {button1, button2, button3, button4};
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setBackground(buttonColor);
            button.setForeground(buttonTextColor);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            button.setFocusPainted(false); // Disable the focus border
            Dimension buttonSize = new Dimension(200, 50);
            button.setPreferredSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setMaximumSize(buttonSize);
        }

        shortAnswerField = new JTextField();
        shortAnswerField.setFont(buttonFont);
        shortAnswerField.setPreferredSize(new Dimension(300, 30));
        shortAnswerField.setMinimumSize(new Dimension(300, 30));
        shortAnswerField.setVisible(false); // Initially hidden
        shortAnswerField.addActionListener(e -> shortAnswerHandle(shortAnswerField.getText()));

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

        updateNavigationButtons();
    }

    /**
     * Creates an arrow button for navigating the maze.
     *
     * @param text      The text to display on the button.
     * @param direction The direction the button represents.
     * @param size      The size of the button.
     * @return The created JButton.
     */
    private JButton createArrowButton(String text, String direction, Dimension size) {
        JButton button = new JButton(text);
        button.setPreferredSize(size);
        button.setMinimumSize(size);
        button.setMaximumSize(size);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.addActionListener(e -> attemptMove(direction));
        return button;
    }

    /**
     * Handles the event when a short answer is submitted.
     *
     * @param text The text of the answer submitted.
     */
    private void shortAnswerHandle(String text) {
        Question currentQuestion = maze.getCurrentQuestion();
        if (currentQuestion != null) {
            boolean isCorrect = currentQuestion.getAnswer().equalsIgnoreCase(text);
            if (isCorrect) {
                JOptionPane.showMessageDialog(this, "Correct! You have moved to the next room.");
                clearQuestionUI();
                movePendingDirection();
                updateRoomNumber();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect! The door is now locked.");
                maze.lockCurrentDoor(Arrays.toString(maze.getDirectionFromRoomNumber(maze.getTargetRoomNumber(pendingDirection))));
                clearQuestionUI();
            }
            updateNavigationButtons();
            checkGameState();
        } else {
            JOptionPane.showMessageDialog(this, "No active question.");
        }
    }

    /**
     * Attempts to move in the specified direction.
     *
     * @param direction The direction to move.
     */
    private void attemptMove(String direction) {
        this.pendingDirection = direction;
        int targetRoomNumber = maze.getTargetRoomNumber(direction);
        if (maze.attemptMove(targetRoomNumber)) {
            movePendingDirection();
            updateRoomNumber();
            updateNavigationButtons();
        } else if (maze.isDirectionBlocked(direction)) {
            JOptionPane.showMessageDialog(this, "This door is now locked.");
            checkGameState();
        } else {
            Question currentQuestion = maze.getCurrentQuestion();
            if (currentQuestion != null) {
                String questionType = currentQuestion.getType().name();
                String questionText = currentQuestion.getQuestion();
                String[] choices = currentQuestion.getChoices();
                updateQuestionUI(questionType, questionText, choices);
                setArrowButtonsEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "No active question.");
            }
        }
    }

    /**
     * Handles the event when an answer button is pressed.
     *
     * @param answer The answer text.
     */
    private void handleAnswer(String answer) {
        Question currentQuestion = maze.getCurrentQuestion();
        if (currentQuestion != null) {
            boolean isCorrect = currentQuestion.getAnswer().equalsIgnoreCase(answer);
            if (isCorrect) {
                JOptionPane.showMessageDialog(this, "Correct! You have moved to the next room.");
                clearQuestionUI();
                movePendingDirection();
                updateRoomNumber();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect! The door is now locked.");
                maze.lockCurrentDoor(pendingDirection);
                clearQuestionUI();
            }
            updateNavigationButtons();
            checkGameState();
        } else {
            JOptionPane.showMessageDialog(this, "No active question.");
        }
    }

    /**
     * Moves to the pending direction if set.
     */
    private void movePendingDirection() {
        if (pendingDirection != null) {
            int targetRoomNumber = maze.getTargetRoomNumber(pendingDirection);
            maze.move(targetRoomNumber, true);
            pendingDirection = null;
        }
        setArrowButtonsEnabled(true);
    }

    /**
     * Clears the question UI elements.
     */
    private void clearQuestionUI() {
        questionLabel.setText("");
        button1.setVisible(false);
        button2.setVisible(false);
        button3.setVisible(false);
        button4.setVisible(false);
        shortAnswerField.setVisible(false);
        revalidate();
        repaint();
    }

    /**
     * Updates the room number label with the current room number.
     */
    public void updateRoomNumber() {
        int currentRoomNumber = maze.getCurrentRoomNumber();
        roomNumberLabel.setText("Room " + currentRoomNumber);
    }

    /**
     * Updates the question UI elements based on the question type and text.
     *
     * @param questionType The type of the question.
     * @param questionText The text of the question.
     * @param choices      The choices for the question.
     */
    private void updateQuestionUI(String questionType, String questionText, String[] choices) {
        questionLabel.setText("Question: " + questionText);
        switch (questionType) {
            case "multipleChoice" -> {
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
            case "shortAnswer" -> {
                button1.setVisible(false);
                button2.setVisible(false);
                button3.setVisible(false);
                button4.setVisible(false);
                shortAnswerField.setVisible(true);
                shortAnswerField.setText("");
            }
            case "trueFalse" -> {
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

    /**
     * Updates the navigation buttons based on the possible moves.
     */
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

    /**
     * Enables or disables the arrow buttons.
     *
     * @param enabled True to enable the buttons, false to disable.
     */
    private void setArrowButtonsEnabled(boolean enabled) {
        upButton.setEnabled(enabled);
        downButton.setEnabled(enabled);
        leftButton.setEnabled(enabled);
        rightButton.setEnabled(enabled);
    }

    /**
     * Checks the game state for win or loss conditions.
     */
    private void checkGameState() {
        if (maze.isAtExit()) {
            int choice = JOptionPane.showOptionDialog(this, "Congratulations! You've reached the exit. What would you like to do?", "You Win!",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    new Object[]{"Play Again", "Start Menu", "Exit"}, null);

            switch (choice) {
                case JOptionPane.YES_OPTION:
                    gameWindow.restartGame();
                    break;
                case JOptionPane.NO_OPTION:
                    gameWindow.showStartMenu();
                    break;
                case JOptionPane.CANCEL_OPTION:
                    System.exit(0);
                    break;
            }
        } else if (!maze.isPathToEnd()) {
            int choice = JOptionPane.showOptionDialog(this, "There is no path to the end. The game is over. What would you like to do?", "Game Over",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.ERROR_MESSAGE, null,
                    new Object[]{"Play Again", "Start Menu", "Exit"}, null);

            switch (choice) {
                case JOptionPane.YES_OPTION:
                    gameWindow.restartGame();
                    break;
                case JOptionPane.NO_OPTION:
                    gameWindow.showStartMenu();
                    break;
                case JOptionPane.CANCEL_OPTION:
                    System.exit(0);
                    break;
            }
        }
    }
}
