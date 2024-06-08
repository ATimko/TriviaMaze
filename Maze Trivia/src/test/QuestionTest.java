package test;
import static org.junit.Assert.*;

import model.Question;
import model.QuestionMultipleChoice;
import model.QuestionShortAnswer;
import model.QuestionTrueFalse;
import org.junit.Test;
public class QuestionTest {
    @Test
    public void testMultipleChoiceQuestion() {
        // Test short answer question
        Question question = new QuestionMultipleChoice("What is 2 + 2?", new String[]{"1", "2", "3", "4"}, "4");

        // Test question type
        assertEquals(Question.questionType.multipleChoice, question.getType());

        // Test question text
        assertEquals("What is 2 + 2?", question.getQuestion());

        // Test choices
        assertArrayEquals(new String[]{"1", "2", "3","4"}, question.getChoices());

        // Test correct answer
        assertTrue(question.correctAnswer("4"));
        assertFalse(question.correctAnswer("1"));
        assertFalse(question.correctAnswer("2"));
        assertFalse(question.correctAnswer("3"));
    }

    @Test
    public void testTrueFalseQuestion() {
        // Test true/false question
        Question question = new QuestionTrueFalse("Is the sun hot?", new String[]{"True", "False"}, "True");

        // Test question type
        assertEquals(Question.questionType.trueFalse, question.getType());

        // Test question text
        assertEquals("Is the sun hot?", question.getQuestion());

        // Test choices
        assertArrayEquals(new String[]{"True", "False"}, question.getChoices());

        // Test correct answer
        assertTrue(question.correctAnswer("True"));
        assertFalse(question.correctAnswer("False"));

    }

    @Test
    public void testShortAnswerQuestion() {
        // Test short answer question
        Question question = new QuestionShortAnswer("What is the capital of France?", new String[]{""}, "Paris");

        // Test question type
        assertEquals(Question.questionType.shortAnswer, question.getType());

        // Test question text
        assertEquals("What is the capital of France?", question.getQuestion());

        // Test choices
        assertArrayEquals(new String[]{""}, question.getChoices());
        //assertArrayEquals(new String[]{" "}, question.getChoices());

        // Test correct answer
        assertTrue(question.correctAnswer("Paris"));
        assertTrue(question.correctAnswer("PARIS"));
        assertTrue(question.correctAnswer("pArIS"));
        assertFalse(question.correctAnswer(""));
        assertFalse(question.correctAnswer(" "));

    }
}
