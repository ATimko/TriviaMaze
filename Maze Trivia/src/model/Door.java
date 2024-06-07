package model;
import java.util.Arrays;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;

import java.util.Scanner;

public class Door {
    private boolean locked;
    private boolean questionAnsweredCorrectly;
    private boolean visited; // Add this attribute
    private static String questionText;
    private static String[] answerChoices;
    private static String[] getAnswerChoice;
    private static Question question;

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

    public boolean askQuestion(Question question) {
        // Set the question for the door
        Door.question = question;

        // Simulate presenting the question to the player and getting an answer
        boolean answerCorrect = true;

        if (answerCorrect) {
            unlock();
            markVisited();
            return true;
        } else {
            lock();
            return false;
        }
    }
    /*
    public static String getQuestionString() {
        if (question != null) {
            return question.getQuestion();
        } else {
            return "";
        }
    }

     */
    public static String getQuestionString() {
        return question != null ? question.getQuestion() : "";
    }

    public static String[] getAnswerChoice(){
        return answerChoices = question.getChoices();
    }

    public void markVisited() {
        this.visited = true;
    }

    public void enterNewRoom() {
        System.out.println("NEW ROOM!");
    }
}
