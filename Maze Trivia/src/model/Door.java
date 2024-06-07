package model;
import java.util.Arrays;
import java.util.Random;

import java.util.Scanner;

public class Door {
    private boolean locked;
    private boolean questionAnsweredCorrectly;
    private boolean visited; // Add this attribute
    private static String questionText;
    private static String[] answerChoices;
    private static Question question;
    private String questionType;

    public Door() {
        this.locked = false; // Initially, the door is unlocked
        this.questionAnsweredCorrectly = false;
        this.visited = false; // Initially, the door is not visited
        question = DatabaseQuery.getRandomQuestionByType(Question.questionType.valueOf(questionType));
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
        if (randI == 0) {
            questionType = "shortAnswer";
        } else if (randI == 1) {
            questionType = "multipleChoice";
        } else { // randI == 2
            questionType = "trueFalse";
        }

        if (!questionAnsweredCorrectly && !visited) { // Modify condition
            System.out.println(question.getQuestion());
            //System.out.println(Arrays.toString(answerChoices));
            questionText = question.getQuestion();
            answerChoices = question.getChoices();
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
        return questionText = question.getQuestion();
    }
    public static String[] getAnswerChoices(){
        return answerChoices = question.getChoices();
    }

    public void markVisited() {
        this.visited = true;
        //System.out.println("DEBUG: Door marked as visited");
    }

    public void enterNewRoom() {
        System.out.println("NEW ROOM!");
    }
}
