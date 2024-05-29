package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseTest {
    public static void main(String[] args) {
        // Ensure the table is created
        DatabaseManager.createTable();

        // Import questions to populate the table
        QuestionImporter.importQuestions("questions.txt");

        // Display the contents of the questions table
        try (Connection connection = DatabaseManager.connect();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT * FROM questions");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String subject = resultSet.getString("subject");
                String question = resultSet.getString("question");
                String choices = resultSet.getString("choices");
                String answer = resultSet.getString("answer");

                System.out.printf("ID: %d, Type: %s, Subject: %s, Question: %s, Choices: %s, Answer: %s%n",
                        id, type, subject, question, choices, answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
