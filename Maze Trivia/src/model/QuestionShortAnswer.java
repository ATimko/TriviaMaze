package model;

/**
 * Represents a short-answer question.
 */
public class QuestionShortAnswer extends AbstractQuestion {

    /**
     * Constructs a new short-answer question.
     *
     * @param question The question text.
     * @param choices The possible answer choices (typically empty for short-answer questions).
     * @param answer The correct answer.
     */
    public QuestionShortAnswer(String question, String[] choices, String answer) {
        super(questionType.shortAnswer, question, choices, answer);
    }
}
