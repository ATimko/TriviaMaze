package model;
import java.util.Random;

import java.util.Scanner;

public class Door {
    private boolean locked;
    private boolean questionAnsweredCorrectly;
    private boolean visited; // Add this attribute
    private static String questionText;

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
        Random rand = new Random();
        int randI = rand.nextInt(3);
        String questionType;

        if (randI == 0) {
            questionType = "shortAnswer";
        } else if (randI == 1) {
            questionType = "multipleChoice";
        } else { // randI == 2
            questionType = "trueFalse";
        }

        Question question = DatabaseQuery.getRandomQuestionByType(Question.questionType.valueOf(questionType));
        if (!questionAnsweredCorrectly && !visited) { // Modify condition
            System.out.println(question.getQuestion());
            questionText = question.getQuestion();
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if (question.correctAnswer(answer)) {
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
    public static String getQuestionString(){
        return questionText;
    }

    public void markVisited() {
        this.visited = true;
        //System.out.println("DEBUG: Door marked as visited");
    }

    public void enterNewRoom() {
        System.out.println("NEW ROOM!");
    }
}
