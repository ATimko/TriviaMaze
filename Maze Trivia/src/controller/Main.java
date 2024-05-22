package controller;

import database.DatabaseManager;
import database.QuestionImporter;
import views.GameWindow;
import javax.swing.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        DatabaseManager.createTable();
        QuestionImporter.importQuestions("questions.txt");
        printQuestions();


        //SwingUtilities.invokeLater(GameWindow::new);
    }
    private static void printQuestions() {
        String sql = "SELECT * FROM questions";

        try (Connection conn = DatabaseManager.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (!rs.isBeforeFirst()) {
                System.out.println("No questions found in the database.");
                return;
            }

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Type: " + rs.getString("type"));
                System.out.println("Subject: " + rs.getString("subject"));
                System.out.println("Question: " + rs.getString("question"));
                System.out.println("Choices: " + rs.getString("choices"));
                System.out.println("Answer: " + rs.getString("answer"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
