package maze;

import java.util.Scanner;

public class Door {
    private boolean locked;
    private boolean questionAnsweredCorrectly;

    public Door() {
        this.locked = false; // Initially, the door is unlocked
        this.questionAnsweredCorrectly = false;
    }

    public boolean isLocked() {
        return locked;
    }

    public boolean isUnlocked() {
        return !locked;
    }

    public void lock() {
        this.locked = true;
    }

    public void unlock() {
        this.locked = false;
    }

    public boolean askQuestion() {
        if (!questionAnsweredCorrectly) {
            System.out.println("What is the capital of France?");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Paris")) {
                questionAnsweredCorrectly = true;
                unlock();
                return true;
            } else {
                lock();
                return false;
            }
        }
        return true; // If the question has already been answered correctly, allow passage
    }

    public void enterNewRoom() {
    }
}
