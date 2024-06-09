package model;

/**
 * Represents a true/false question.
 */
public class QuestionTrueFalse extends AbstractQuestion {

    /**
     * Constructs a new true/false question.
     *
     * @param question The question text.
     * @param choices The possible answer choices (typically "True" and "False").
     * @param answer The correct answer.
     */
    public QuestionTrueFalse(String question, String[] choices, String answer) {
        super(questionType.trueFalse, question, choices, answer);
    }

}
