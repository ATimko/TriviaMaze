package database;

import question.Question;
import question.QuestionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class DatabaseTest {
    public static void main(String[] args) {
        // Ensure the table is created
        DatabaseManager.createTable();

        // Clear the table to avoid duplicates
        clearTable();

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

                Question test = QuestionFactory.createQuestion(question, new String[]{subject}, new String[]{choices}, answer, type);
                System.out.println(test.getType());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void clearTable() {
        try (Connection connection = DatabaseManager.connect();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("DELETE FROM questions");
            System.out.println("Table cleared successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
