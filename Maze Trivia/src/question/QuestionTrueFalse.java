package question;
/**
 * The QuestionTrueFalse class represents a true/false question.
 * It extends the AbstractedQuestion class.
 *
 * Instances of this class are used to create true/false questions
 * with specified subjects and correct answer.
 *
 * Example usage:
 * String[] subjects = {"Math", "Science"};
 * String[] choices = {"True", "False"};
 * String answer = "True";
 * QuestionTrueFalse question = new QuestionTrueFalse("Is the sky blue?", subjects, choices, answer);
 */

public class QuestionTrueFalse extends AbstractQuestion {
    /**
     * Constructs a new QuestionTrueFalse object.
     *
     * @param question the text of the question
     * @param subject  an array of subjects associated with the question
     * @param choices  an array of possible choices for the question, typically "True" and "False"
     * @param answer   the correct answer for the question
     */
    public QuestionTrueFalse(String[] subject, String question, String[] choices, String answer) {
        super(questionType.trueFalse, subject, question, choices, answer);
    }
}
