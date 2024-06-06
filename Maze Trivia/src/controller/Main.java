package controller;

import model.DatabaseManager;
import model.QuestionImporter;
import model.MazeFactory;
import view.GameWindow;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.createTable();
        MazeFactory.createMaze();
        QuestionImporter.importQuestions("questions.txt");
        SwingUtilities.invokeLater(GameWindow::new);
    }
}
