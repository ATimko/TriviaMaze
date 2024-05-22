package maze;

import java.util.Scanner;

public class Door {
    private boolean isLocked;
    private boolean isUnlocked;

    public Door() {
        isLocked = false;
        isUnlocked = false;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void lock() {
        isLocked = true;
    }

    public void unlock() {
        isUnlocked = true;
    }

    public boolean askQuestion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the capital of France?");
        String answer = scanner.nextLine();
        return "Paris".equalsIgnoreCase(answer);
    }

    public void enterNewRoom() {
        System.out.println("You entered a new room!");
    }
}
