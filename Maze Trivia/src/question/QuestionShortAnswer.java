package question;
/**
 * The QuestionShortAnswer class represents a short answer question.
 * It extends the AbstractedQuestion class.
 * <p>
 * Instances of this class are used to create short answer questions
 * with specified subjects and correct answer.
 * </p>
 * <pre>
 * Example usage:
 * String[] subjects = {"Literature", "History"};
 * String[] choices = {}; // Choices are typically not needed for short answer questions
 * String answer = "Shakespeare";
 * QuestionShortAnswer question = new QuestionShortAnswer("Who wrote Hamlet?", subjects, choices, answer);
 * </pre>
 */
public class QuestionShortAnswer extends AbstractQuestion {
    /**
     * Constructs a new QuestionShortAnswer object.
     *
     * @param question the text of the question
     * @param subject  an array of subjects associated with the question
     * @param choices  an array of possible choices for the question; typically not used for short answer questions
     * @param answer   the correct answer for the question
     */
    public QuestionShortAnswer(String[] subject, String question, String[] choices, String answer) {
        super(questionType.shortAnswer, subject, question, choices, answer);
    }
}
