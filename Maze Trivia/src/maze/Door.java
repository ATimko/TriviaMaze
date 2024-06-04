package maze;

import java.util.Scanner;

public class Door {
    private boolean locked;
    private boolean questionAnsweredCorrectly;
    private boolean visited; // Add this attribute

    public Door() {
        this.locked = false; // Initially, the door is unlocked
        this.questionAnsweredCorrectly = false;
        this.visited = false; // Initially, the door is not visited
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
        //System.out.println("DEBUG: Asking question for door, visited=" + visited + ", questionAnsweredCorrectly=" + questionAnsweredCorrectly);
        if (!questionAnsweredCorrectly && !visited) { // Modify condition
            System.out.println("What is the capital of France?");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase("Paris")) {
                questionAnsweredCorrectly = true;
                unlock();
                visited = true; // Mark the door as visited
                return true;
            } else {
                lock();
                return false;
            }
        }
        return true; // If the question has already been answered correctly or visited, allow passage
    }

    public void markVisited() {
        this.visited = true;
        //System.out.println("DEBUG: Door marked as visited");
    }

    public void enterNewRoom() {
        System.out.println("NEW ROOM!");
    }
}
