package Maze;

import javax.swing.*;

public class Door {
    private boolean isLocked;

    public Door() {
        isLocked = false;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void lock() {
        isLocked = true;
    }

    public boolean askQuestion() {
        // Placeholder for question logic
        // Replace this with actual question and answer checking logic
        int response = JOptionPane.showConfirmDialog(null, "Is this a correct answer?", "Question", JOptionPane.YES_NO_OPTION);
        return response == JOptionPane.YES_OPTION;
    }

    public void enterNewRoom() {
        // Placeholder for entering a new room
        JOptionPane.showMessageDialog(null, "You entered a new room!");
    }
}
