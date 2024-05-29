package database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QuestionImporter {

    private static final String INSERT_SQL = """
        INSERT INTO questions(type, subject, question, choices, answer) VALUES(?, ?, ?, ?, ?)
        """;

    public static void importQuestions(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             Connection conn = DatabaseManager.connect();
             PreparedStatement prep = conn.prepareStatement(INSERT_SQL)) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String type = extractValue(line, "type");
                String subject = extractValue(br.readLine(), "subject");
                String question = extractValue(br.readLine(), "question");
                String choices = extractValue(br.readLine(), "choices");
                String answer = extractValue(br.readLine(), "answer");

                prep.setString(1, type);
                prep.setString(2, subject);
                prep.setString(3, question);
                prep.setString(4, choices);
                prep.setString(5, answer);

                prep.addBatch();

                // Skip the empty line between questions
                br.readLine();
            }
            prep.executeBatch();
            System.out.println("Questions imported successfully.");
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static String extractValue(String line, String expectedKey) throws IOException {
        if (line == null || !line.startsWith(expectedKey + "=")) {
            throw new IOException("Invalid or missing line for " + expectedKey + ": " + line);
        }
        String[] parts = line.split("=", 2);
        if (parts.length < 2) {
            throw new IOException("Invalid format for " + expectedKey + ": " + line);
        }
        return parts[1];
    }
}
