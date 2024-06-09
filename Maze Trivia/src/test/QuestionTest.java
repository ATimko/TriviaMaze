package test;

import model.Question;
import model.QuestionFactory;

import org.junit.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    @Test
    public void getQuestion() {
        Question question = QuestionFactory.createQuestion("Testing", new String[]{"True", "False"}, "True", "trueFalse");
        assertEquals("Testing", question.getQuestion());
    }

    @Test
    public void getQuestionEmptyString() {
        Question question = QuestionFactory.createQuestion("", new String[]{"Testing"}, "Testing", "shortAnswer");
        assertEquals("", question.getQuestion());
    }

    @Test
    public void getChoicesTrueFalse() {
        Question question = QuestionFactory.createQuestion("Testing", new String[]{"True", "False"}, "True", "trueFalse");
        assertEquals(Arrays.toString(question.getChoices()), Arrays.toString(new String[]{"True", "False"}));
    }

    @Test
    public void getChoicesShortAnswer() {
        Question question = QuestionFactory.createQuestion("Testing", new String[]{""}, "", "shortAnswer");
        assertEquals(1, question.getChoices().length);
    }

    @Test
    public void getChoicesShortAnswerEmpty() {
        Question question = QuestionFactory.createQuestion("Testing", new String[]{}, "True", "shortAnswer");
        assertEquals(0, question.getChoices().length);
    }

    @Test
    public void getChoicesMultipleChoice() {
        Question question = QuestionFactory.createQuestion("Testing", new String[]{"A", "B", "C", "D"}, "A", "multipleChoice");
        assertEquals(Arrays.toString(question.getChoices()), Arrays.toString(new String[]{"A", "B", "C", "D"}));
    }
    @Test
    public void testGetTypeTrueFalse() {
        // Create a true/false question
        String questionText = "Is the sky blue?";
        String[] choices = {"True", "False"};
        String correctAnswer = "True";
        String type = "trueFalse";
        Question question = QuestionFactory.createQuestion(questionText, choices, correctAnswer, type);

        // Check if the type of the question is true/false
        assertEquals(Question.questionType.trueFalse, question.getType(), "Incorrect question type");
    }

    @Test
    public void testGetQuestionTrueFalse() {
        // Create a true/false question
        String questionText = "Is the sky blue?";
        String[] choices = {"True", "False"};
        String correctAnswer = "True";
        String type = "trueFalse";
        Question question = QuestionFactory.createQuestion(questionText, choices, correctAnswer, type);

        // Check if the question text is correct
        assertEquals("Is the sky blue?", question.getQuestion(), "Incorrect question text");
    }

    @Test
    public void testGetAnswerTrueFalse() {
        // Create a true/false question
        String questionText = "Is the sky blue?";
        String[] choices = {"True", "False"};
        String correctAnswer = "True";
        String type = "trueFalse";
        Question question = QuestionFactory.createQuestion(questionText, choices, correctAnswer, type);

        // Check if the correct answer is correct
        assertEquals("True", question.getAnswer(), "Incorrect correct answer");
    }
    @Test
    public void testGetTypeMultipleChoice() {
        // Create a Multiple Choice question
        String questionText = "What color is the sky";
        String[] choices = {"Blue", "Red", "Purple", "Orange"};
        String correctAnswer = "Blue";
        String type = "multipleChoice";
        Question question = QuestionFactory.createQuestion(questionText, choices, correctAnswer, type);

        // Check if the type of the question is multipleChoice
        assertEquals(Question.questionType.multipleChoice, question.getType(), "Incorrect question type");
    }

    @Test
    public void testGetQuestionMultipleChoice() {
        // Create a Multiple Choice question
        String questionText = "What color is the sky?";
        String[] choices = {"Blue", "Red", "Purple", "Orange"};
        String correctAnswer = "Blue";
        String type = "multipleChoice";
        Question question = QuestionFactory.createQuestion(questionText, choices, correctAnswer, type);

        // Check if the question text is correct
        assertEquals("What color is the sky?", question.getQuestion(), "Incorrect question text");
    }

    @Test
    public void testGetAnswerMultipleChoice() {
        // Create a Multiple Choice question
        String questionText = "What color is the sky?";
        String[] choices = {"Blue", "Red", "Purple", "Orange"};
        String correctAnswer = "Blue";
        String type = "multipleChoice";
        Question question = QuestionFactory.createQuestion(questionText, choices, correctAnswer, type);

        // Check if the correct answer is correct
        assertEquals("Blue", question.getAnswer(), "Incorrect correct answer");
    }
    @Test
    public void testGetTypeShortAnswer() {
        // Create a Short Answer question
        String questionText = "What color is the sky";
        String[] choices = {""};
        String correctAnswer = "Blue";
        String type = "shortAnswer";
        Question question = QuestionFactory.createQuestion(questionText, choices, correctAnswer, type);

        // Check if the type of the question is shortAnswer
        assertEquals(Question.questionType.shortAnswer, question.getType(), "Incorrect question type");
    }

    @Test
    public void testGetQuestionShortAnswer() {
        // Create a Short Answer question
        String questionText = "What color is the sky?";
        String[] choices = {""};
        String correctAnswer = "Blue";
        String type = "shortAnswer";
        Question question = QuestionFactory.createQuestion(questionText, choices, correctAnswer, type);

        // Check if the question text is correct
        assertEquals("What color is the sky?", question.getQuestion(), "Incorrect question text");
    }

    @Test
    public void testGetAnswerShortAnswer() {
        // Create a Short Answer question
        String questionText = "What color is the sky?";
        String[] choices = {""};
        String correctAnswer = "Blue";
        String type = "shortAnswer";
        Question question = QuestionFactory.createQuestion(questionText, choices, correctAnswer, type);

        // Check if the correct answer is correct
        assertEquals("Blue", question.getAnswer(), "Incorrect correct answer");
    }
}
