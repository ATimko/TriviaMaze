package test;

import model.Question;
import model.QuestionFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class QuestionTest {
    private Question simpleQuestionTrueFalse;
    private Question simpleQuestionMultipleChoice;
    private Question simpleQuestionShortAnswer;

    @BeforeEach
    public void setUp() {
        simpleQuestionTrueFalse = QuestionFactory.createQuestion("Testing", new String[]{"True", "False"}, "True", "trueFalse");
        simpleQuestionMultipleChoice = QuestionFactory.createQuestion("Testing", new String[]{"Test1", "Test2", "Test3"}, "Test1", "multipleChoice");
        simpleQuestionShortAnswer = QuestionFactory.createQuestion("Testing", new String[]{}, "Test", "shortAnswer");
    }

    @Test
    public void getType() {
        assertAll("Tests if the question type is correct.",
                () -> assertEquals(simpleQuestionTrueFalse.getType(), Question.questionType.trueFalse, "Incorrect question type, it should be trueFalse"),
                () -> assertEquals(simpleQuestionMultipleChoice.getType(), Question.questionType.multipleChoice, "Incorrect question type, it should be multipleChoice"),
                () -> assertEquals(simpleQuestionShortAnswer.getType(), Question.questionType.shortAnswer, "Incorrect question type, it should be shortAnswer")
        );
    }
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
        Question question = QuestionFactory.createQuestion("Testing", new String[]{"A", "B", "C", "D"}, "True", "multipleChoice");
        assertEquals(Arrays.toString(question.getChoices()), Arrays.toString(new String[]{"A", "B", "C", "D"}));
    }
}
