package controller;

import database.DatabaseManager;
import database.QuestionImporter;
import views.GameWindow;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.createTable();
        //questionImporter.importQuestions("Database/questions.txt");

        SwingUtilities.invokeLater(GameWindow::new);
    }
}
