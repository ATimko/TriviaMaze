package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Class for importing questions from a file into the database.
 */
public class QuestionImporter {

    private static final String INSERT_SQL = """
        INSERT INTO questions(type, question, choices, answer) VALUES(?, ?, ?, ?)
        """;

    /**
     * Imports questions from a specified file into the database.
     *
     * @param filePath The path to the file containing the questions.
     */
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
                String question = extractValue(br.readLine(), "question");
                String choices = extractValue(br.readLine(), "choices");
                String answer = extractValue(br.readLine(), "answer");

                prep.setString(1, type);
                prep.setString(2, question);
                prep.setString(3, choices);
                prep.setString(4, answer);

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

    /**
     * Extracts the value for a specified key from a line.
     *
     * @param line The line to extract the value from.
     * @param expectedKey The expected key at the start of the line.
     * @return The extracted value.
     * @throws IOException If the line is invalid or the expected key is not found.
     */
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
