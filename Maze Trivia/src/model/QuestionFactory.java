package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuestionFactory {
    public static Question createQuestion(final String theQuestion, final String[] theChoices, final String theAnswer, final String type) {
        return switch (type) {
            case "multipleChoice" -> new QuestionMultipleChoice(theQuestion, theChoices, theAnswer) {};
            case "trueFalse" -> new QuestionTrueFalse(theQuestion, theChoices, theAnswer) {};
            case "shortAnswer" -> new QuestionShortAnswer(theQuestion, theChoices, theAnswer) {};
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
    public static Question getRandomQuestion() {
        final String sql = "SELECT * FROM questions ORDER BY RANDOM() LIMIT 1";

        try (Connection conn = DatabaseManager.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            final ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                final String type = rs.getString("type");
                final String questionText = rs.getString("theQuestion");
                final String choicesStr = rs.getString("theChoices");
                final String answer = rs.getString("answer");

                final String[] choices = choicesStr.isEmpty() ? new String[0] : choicesStr.split(",");

                return createQuestion(questionText, choices, answer, type);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
