package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseQuery {

    public static Question getRandomQuestionByType(Question.questionType questionType) {
        String sql = "SELECT * FROM questions WHERE type = ? ORDER BY RANDOM() LIMIT 1";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, questionType.name());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String type = rs.getString("type");
                String questionText = rs.getString("question");
                String choicesStr = rs.getString("choices");
                String answer = rs.getString("answer");

                String[] choices = choicesStr.isEmpty() ? new String[0] : choicesStr.split(",");

                return QuestionFactory.createQuestion(questionText, choices, answer, type);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
