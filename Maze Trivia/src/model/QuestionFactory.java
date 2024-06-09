package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Factory class for creating different types of questions.
 */
public class QuestionFactory {

    /**
     * Creates a question based on the specified type.
     *
     * @param question The text of the question.
     * @param choices  The choices available for the question.
     * @param answer   The correct answer to the question.
     * @param type     The type of the question (multipleChoice, trueFalse, or shortAnswer).
     * @return A Question object of the specified type.
     * @throws IllegalStateException if the question type is unexpected.
     */
    public static Question createQuestion(String question, String[] choices, String answer, String type) {
        return switch (type) {
            case "multipleChoice" -> new QuestionMultipleChoice(question, choices, answer) {};
            case "trueFalse" -> new QuestionTrueFalse(question, choices, answer) {};
            case "shortAnswer" -> new QuestionShortAnswer(question, choices, answer) {};
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }

    /**
     * Retrieves a random question from the database.
     *
     * @return A randomly selected Question object from the database, or null if an error occurs or no question is found.
     */
    public static Question getRandomQuestion() {
        String sql = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String type = rs.getString("type");
                String questionText = rs.getString("question");
                String choicesStr = rs.getString("choices");
                String answer = rs.getString("answer");

                String[] choices = choicesStr.isEmpty() ? new String[0] : choicesStr.split(",");

                return createQuestion(questionText, choices, answer, type);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
