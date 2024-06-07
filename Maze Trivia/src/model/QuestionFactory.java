package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionFactory {
    public static Question createQuestion(String question, String[] choices, String answer, String type) {
        return switch (type) {
            case "multipleChoice" -> new QuestionMultipleChoice(question, choices, answer) {};
            case "trueFalse" -> new QuestionShortAnswer(question, choices, answer) {};
            case "shortAnswer" -> new QuestionTrueFalse(question, choices, answer) {};
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
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
