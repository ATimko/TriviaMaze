package test;

import model.Question;
import model.QuestionFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class QuestionTest {
    private Question mySimpleQuestionTrueFalse;
    private Question mySimpleQuestionMultipleChoice;
    private Question mySimpleQuestionShortAnswer;

    @BeforeEach
    public void setUp() {
        mySimpleQuestionTrueFalse = QuestionFactory.createQuestion("Testing", new String[]{"True", "False"}, "True", "trueFalse");
        mySimpleQuestionMultipleChoice = QuestionFactory.createQuestion("Testing", new String[]{"Test1", "Test2", "Test3"}, "Test1", "multipleChoice");
        mySimpleQuestionShortAnswer = QuestionFactory.createQuestion("Testing", new String[]{}, "Test", "shortAnswer");
    }

    @Test
    public void getType() {
        assertAll("Tests if the question type is correct.",
                () -> assertEquals(mySimpleQuestionTrueFalse.getType(), Question.QUESTIONTYPE.trueFalse, "Incorrect question type, it should be trueFalse"),
                () -> assertEquals(mySimpleQuestionMultipleChoice.getType(), Question.QUESTIONTYPE.multipleChoice, "Incorrect question type, it should be multipleChoice"),
                () -> assertEquals(mySimpleQuestionShortAnswer.getType(), Question.QUESTIONTYPE.shortAnswer, "Incorrect question type, it should be shortAnswer")
        );
    }
    @Test
    public void getQuestion() {
        final Question question = QuestionFactory.createQuestion("Testing", new String[]{"True", "False"}, "True", "trueFalse");
        assertEquals("Testing", question.getQuestion());
    }

    @Test
    public void getQuestionEmptyString() {
        final Question question = QuestionFactory.createQuestion("", new String[]{"Testing"}, "Testing", "shortAnswer");
        assertEquals("", question.getQuestion());
    }

    @Test
    public void getChoicesTrueFalse() {
        final Question question = QuestionFactory.createQuestion("Testing", new String[]{"True", "False"}, "True", "trueFalse");
        assertEquals(Arrays.toString(question.getChoices()), Arrays.toString(new String[]{"True", "False"}));
    }

    @Test
    public void getChoicesShortAnswer() {
        final Question question = QuestionFactory.createQuestion("Testing", new String[]{""}, "", "shortAnswer");
        assertEquals(1, question.getChoices().length);
    }

    @Test
    public void getChoicesShortAnswerEmpty() {
        final Question question = QuestionFactory.createQuestion("Testing", new String[]{}, "True", "shortAnswer");
        assertEquals(0, question.getChoices().length);
    }

    @Test
    public void getChoicesMultipleChoice() {
        final Question question = QuestionFactory.createQuestion("Testing", new String[]{"A", "B", "C", "D"}, "A", "multipleChoice");
        assertEquals(Arrays.toString(question.getChoices()), Arrays.toString(new String[]{"A", "B", "C", "D"}));
    }
}
