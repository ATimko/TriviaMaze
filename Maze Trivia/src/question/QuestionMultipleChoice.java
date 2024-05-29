package question;
/**
 * The QuestionMultipleChoice class represents a multiple-choice question.
 * It extends the AbstractedQuestion class.
 * Instances of this class are used to create multiple-choice questions
 * with specified subjects, choices, and correct answer.
 *
 * Example usage:
 * String[] subjects = {"Math", "Science"};
 * String[] choices = {"A", "B", "C", "D"};
 * String answer = "A";
 * QuestionMultipleChoice question = new QuestionMultipleChoice("What is 2 + 2?", subjects, choices, answer);
 */
public class QuestionMultipleChoice extends AbstractQuestion {
    private String[] choices;
    /**
     * Constructs a new QuestionMultipleChoice object.
     *
     * @param question the text of the question
     * @param subject  an array of subjects associated with the question
     * @param choices  an array of possible choices for the question
     * @param answer   the correct answer for the question
     */
    public QuestionMultipleChoice(String[] subject, String question, String[] choices, String answer) {
        super(questionType.multipleChoice, subject, question, choices, answer);
    }

    // Getters and setters

    public String[] getChoices() {
        return choices;
    }
}
