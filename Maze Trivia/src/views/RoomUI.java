package views;

import maze.Maze;

import javax.swing.*;
import java.awt.*;

public class RoomUI extends JPanel {

    private JLabel roomNumberLabel;
    private JLabel questionLabel;
    private JButton[] answerButtons;
    private JTextField shortAnswerField;
    private boolean isShortAnswer;
    private JPanel gridPanel;
    private Maze maze;

    public RoomUI(Maze maze) {
        this.maze = maze;

        // Set the layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20); // Adjusted padding for larger window

        // Initialize components with larger font
        Font font = new Font("Arial", Font.BOLD, 24); // Larger font for bigger window

        // Custom Colors
        Color backgroundColor = new Color(60, 63, 65);
        Color textColor = new Color(187, 187, 187);
        Color buttonColor = new Color(75, 110, 175);
        Color buttonTextColor = new Color(255, 255, 255);

        // Set background color
        setBackground(backgroundColor);

        roomNumberLabel = new JLabel();
        roomNumberLabel.setFont(font);
        roomNumberLabel.setForeground(textColor);
        updateRoomNumber();

        questionLabel = new JLabel("Question?");
        questionLabel.setFont(font);
        questionLabel.setForeground(textColor);

        gridPanel = new JPanel(new GridLayout(5, 5, 5, 5)); // Create a 5x5 grid panel

        for (int i = 0; i < 25; i++) {
            JPanel roomPanel = new JPanel(new BorderLayout());
            roomPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            roomPanel.setBackground(Color.WHITE);
            gridPanel.add(roomPanel);

            JLabel numberLabel = new JLabel(String.valueOf(i + 1), SwingConstants.CENTER);
            numberLabel.setFont(new Font("Arial", Font.BOLD, 30));
            roomPanel.add(numberLabel, BorderLayout.CENTER);
        }

        answerButtons = new JButton[4];
        for (int i = 0; i < 4; i++) {
            answerButtons[i] = new JButton("Answer Choices");
            answerButtons[i].setFont(font);
            answerButtons[i].setPreferredSize(new Dimension(300, 70)); // Larger buttons
            answerButtons[i].setBackground(buttonColor);
            answerButtons[i].setForeground(buttonTextColor);
            answerButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }

        shortAnswerField = new JTextField();
        shortAnswerField.setFont(font);
        shortAnswerField.setPreferredSize(new Dimension(600, 70));
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
        gbc.fill = GridBagConstraints.HORIZONTAL;
        for (int i = 0; i < 4; i++) {
            gbc.gridy = 3 + i / 2;
            gbc.gridx = i % 2;
            add(answerButtons[i], gbc);
        }

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(shortAnswerField, gbc);
    }

    public void updateRoomNumber() {
        int currentRoomNumber = maze.getCurrentRoomNumber();
        roomNumberLabel.setText("Room " + currentRoomNumber);
    }

    public void updateQuestionUI(String questionType, String questionText, String[] choices) {
        questionLabel.setText(questionText);

        if (questionType.equals("multiple_choice")) {
            isShortAnswer = false;
            shortAnswerField.setVisible(false);
            for (int i = 0; i < answerButtons.length; i++) {
                answerButtons[i].setText(choices[i]);
                answerButtons[i].setVisible(true);
            }
        } else if (questionType.equals("short_answer")) {
            isShortAnswer = true;
            for (JButton button : answerButtons) {
                button.setVisible(false);
            }
            shortAnswerField.setVisible(true);
            shortAnswerField.setText("");
        } else if (questionType.equals("true_false")) {
            isShortAnswer = false;
            shortAnswerField.setVisible(false);
            for (int i = 0; i < answerButtons.length; i++) {
                answerButtons[i].setVisible(i < 2); // Only show two buttons
                answerButtons[i].setText(i == 0 ? "True" : "False");
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
