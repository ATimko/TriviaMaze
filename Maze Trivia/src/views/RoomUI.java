package views;

import maze.Maze;

import javax.swing.*;
import java.awt.*;

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

    public RoomUI(Maze maze) {
        this.maze = maze;

        // Set the layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Adjusted padding for larger window

        // Initialize components with larger font
        Font largeFont = new Font("Arial", Font.BOLD, 36); // Larger font for room number and question
        Font gridFont = new Font("Arial", Font.BOLD, 36); // Larger font for grid numbers
        Font buttonFont = new Font("Arial", Font.BOLD, 18); // Font for buttons

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

        JPanel gridPanel = new JPanel(new GridLayout(5, 5, 5, 5)); // Create a 5x5 grid panel
        gridPanel.setPreferredSize(new Dimension(1000, 1000)); // Adjust the size of the grid panel
        gridPanel.setMinimumSize(new Dimension(350, 350));

        for (int i = 0; i < 25; i++) {
            JPanel roomPanel = new JPanel(new BorderLayout());
            roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            roomPanel.setBackground(Color.WHITE);
            roomPanel.setPreferredSize(new Dimension(200, 200)); // Adjust the size of each cell
            gridPanel.add(roomPanel);

            JLabel numberLabel = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
            numberLabel.setFont(gridFont);
            roomPanel.add(numberLabel, BorderLayout.CENTER);
        }

        // Create buttons explicitly
        button1 = new JButton("Answer Choices");
        button2 = new JButton("Answer Choices");
        button3 = new JButton("Answer Choices");
        button4 = new JButton("Answer Choices");

        // Set button properties
        JButton[] buttons = {button1, button2, button3, button4};
        for (JButton button : buttons) {
            button.setFont(buttonFont);
            button.setBackground(buttonColor);
            button.setForeground(buttonTextColor);
            button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            button.setFocusPainted(false); // Disable the focus border
            Dimension buttonSize = new Dimension(1000, 100); // Adjust the button size here
            button.setPreferredSize(buttonSize);
            button.setMinimumSize(buttonSize); // Force the minimum size to be the same as preferred size
            button.setMaximumSize(buttonSize); // Set the maximum size to ensure height doesn't change
        }

        shortAnswerField = new JTextField();
        shortAnswerField.setFont(buttonFont);
        shortAnswerField.setPreferredSize(new Dimension(600, 30));
        shortAnswerField.setMinimumSize(new Dimension(600, 30));
        shortAnswerField.setVisible(false); // Initially hidden

        // Add components to the panel with adjusted positions
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        add(roomNumberLabel, gbc);

        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(questionLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.gridheight = 2;
        add(gridPanel, gbc); // Replaced pictureLabel with gridPanel

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(new JLabel(), gbc); // Empty label for spacing

        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE; // Prevent buttons from expanding
        gbc.insets = new Insets(-50, 10, 5, 10); // Adjusted gap between buttons (reduce the top and bottom insets)

        // Add buttons to layout without weight on y-axis
        gbc.weightx = 0.5;
        gbc.weighty = 0;

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(button1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        add(button2, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(button3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(button4, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(shortAnswerField, gbc);
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
                button1.setText(choices[0]);
                button2.setText(choices[1]);
                button3.setText(choices[2]);
                button4.setText(choices[3]);
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

    /** public static void main(String[] args) {
     // Create the frame to display the UI
     JFrame frame = new JFrame("Room UI");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     // Adjust the size of the frame to 1200x900
     frame.setSize(1200, 900);

     //Maze maze = new Maze(); // Assuming Maze class is properly defined
     RoomUI roomUI = new RoomUI(maze);
     frame.add(roomUI);
     frame.setVisible(true);

     // Example usage
     String[] choices = {"Choice 1", "Choice 2", "Choice 3", "Choice 4"};
     roomUI.updateQuestionUI("multiple_choice", "What is 2+2?", choices);

     // To test short answer
     // roomUI.updateQuestionUI("short_answer", "Describe your solution.");

     // To test true/false
     // roomUI.updateQuestionUI("true_false", "Is the sky blue?");
     }
     */
}
