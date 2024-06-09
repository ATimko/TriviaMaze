package model;

/**
 * Represents a multiple-choice question.
 */
public class QuestionMultipleChoice extends AbstractQuestion {

    /**
     * Constructs a new multiple-choice question.
     *
     * @param question The question text.
     * @param choices The possible answer choices.
     * @param answer The correct answer.
     */
    public QuestionMultipleChoice(String question, String[] choices, String answer) {
        super(questionType.multipleChoice, question, choices, answer);
    }
}
