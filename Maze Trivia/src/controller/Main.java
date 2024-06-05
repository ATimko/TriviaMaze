package controller;

import database.DatabaseManager;
import database.DatabaseQuery;
import database.QuestionImporter;
import maze.Maze;
import maze.MazeFactory;
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
        MazeFactory.createMaze();
        QuestionImporter.importQuestions("questions.txt");
        SwingUtilities.invokeLater(GameWindow::new);
    }
}
