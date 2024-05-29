package controller;

import database.DatabaseManager;
import database.QuestionImporter;
import question.QuestionFactory;
import question.QuestionMultipleChoice;
import question.QuestionShortAnswer;
import question.QuestionTrueFalse;
import views.GameWindow;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.createTable();
        QuestionFactory questionFactory = new QuestionFactory();

        // Example of using the sorted questions
        System.out.println("Multiple Choice Questions:");
        for (QuestionMultipleChoice question : questionFactory.getMultipleChoiceQuestions()) {
            System.out.println(question.getSubject());
        }

        System.out.println("\nTrue/False Questions:");
        for (QuestionTrueFalse question : questionFactory.getTrueFalseQuestions()) {
            System.out.println(question.getSubject());
        }

        System.out.println("\nShort Answer Questions:");
        for (QuestionShortAnswer question : questionFactory.getShortAnswerQuestions()) {
            System.out.println(question.getSubject());
        }
        QuestionImporter.importQuestions("questions.txt");
        SwingUtilities.invokeLater(GameWindow::new);
    }
}
