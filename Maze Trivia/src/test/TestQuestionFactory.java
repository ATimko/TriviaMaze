package test;
import model.Question;
import model.QuestionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class TestQuestionFactory {
    @Test
    public void testCreateQuestion() {
        // Test Data
        String questionText = "What is Scooby Doo's favorite snack?";
        String[] choices = {"Scooby Snacks", "M&M", "Lays", "Dog Food"};
        String answer = "Scooby Snacks";
        String type = "multipleChoice";

        // Create the question
        Question question = QuestionFactory.createQuestion(questionText, choices, answer, type);

        // Verify that the question is not null
        assertNotNull(question);

        // Verify question properties
        assertEquals(questionText, question.getQuestion());
        assertArrayEquals(choices, question.getChoices());
        assertEquals(answer, question.getAnswer());
        assertEquals(Question.questionType.multipleChoice, question.getType());
    }
}
