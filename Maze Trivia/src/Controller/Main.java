package Controller;

import Database.databaseManager;
import Database.questionImporter;
import views.gameWindow;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        databaseManager.createTable();
        //questionImporter.importQuestions("Database/questions.txt");

        //SwingUtilities.invokeLater(gameWindow::new);
    }
}
