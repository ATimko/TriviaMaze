package Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class room {
    private JButton[] doors;
    private boolean[] doorStates; // true if the door is unlocked, false if locked

    public room() {
        doors = new JButton[5];
        doorStates = new boolean[5]; // By default, all doors are locked
        initializeDoors();
    }

    private void initializeDoors() {
        for (int i = 0; i < doors.length; i++) {
            doors[i] = new JButton();
            doors[i].setBounds(getDoorBounds(i));
            doors[i].setContentAreaFilled(false); // Make the button invisible
            doors[i].setBorderPainted(true); // Remove the border
            doors[i].setFocusPainted(true); // Remove the focus outline but I set this for true to see the button
            doors[i].setActionCommand(String.valueOf(i)); // Set action command to door index
            doors[i].addActionListener(new DoorListener());
            doorStates[i] = true; // Initially, all doors are unlocked
        }
    }

    public JButton[] getDoors() {
        return doors;
    }

    private Rectangle getDoorBounds(int doorIndex) {
        // Return the bounds for each door button
        switch (doorIndex) {
            case 0: return new Rectangle(10, 150, 150, 310);
            case 1: return new Rectangle(260, 150, 150, 310);
            case 2: return new Rectangle(515, 150, 150, 310);
            case 3: return new Rectangle(760, 150, 150, 310);
            case 4: return new Rectangle(1012, 150, 150, 310);
            default: return new Rectangle();
        }
    }

    private class DoorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int doorIndex = Integer.parseInt(e.getActionCommand());
            if (doorStates[doorIndex]) {
                // Ask a question
                boolean correctAnswer = askQuestion();
                if (correctAnswer) {
                    // Unlock the door and enter a new room
                    enterNewRoom();
                } else {
                    // Lock the door permanently
                    doorStates[doorIndex] = false;
                    doors[doorIndex].setEnabled(false); // Disable the button to indicate it's locked
                    JOptionPane.showMessageDialog(null, "Incorrect! This door is now locked.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "This door is locked.");
            }
        }

        private boolean askQuestion() {
            // Placeholder for question logic
            // Replace this with actual question and answer checking logic
            int response = JOptionPane.showConfirmDialog(null, "Is this a correct answer?", "Question", JOptionPane.YES_NO_OPTION);
            return response == JOptionPane.YES_OPTION;
        }

        private void enterNewRoom() {
            // Placeholder for entering a new room
            JOptionPane.showMessageDialog(null, "You entered a new room!");
        }
    }
}
