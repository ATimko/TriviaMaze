package controller;

import database.DatabaseManager;
import database.DatabaseQuery;
import database.QuestionImporter;
import question.Question;
import question.Question.questionType;
import views.GameWindow;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.createTable();
        QuestionImporter.importQuestions("questions.txt");
        Question question = DatabaseQuery.getRandomQuestionByType(questionType.shortAnswer);
        if (question != null) {
            System.out.println("Question: " + question.getQuestion());
            if (question.getChoices() != null) {
                System.out.println("Choices: " + String.join(", ", question.getChoices()));
            }

            // Take user input for the answer
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your answer: ");
            String userAnswer = scanner.nextLine();

            // Check if the answer is correct
            if (question.correctAnswer(userAnswer)) {
                System.out.println("Correct!");
            } else {
                System.out.println("Incorrect. The correct answer is: " + question.getAnswer());
            }
        } else {
            System.out.println("No question found for the specified type.");
        }

        // SwingUtilities.invokeLater(GameWindow::new);


    }
}
