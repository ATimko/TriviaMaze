package model;

/**
 * The AbstractedQuestion class represents a question with multiple choices and an answer.
 * It implements the Question interface.
 */
public class AbstractQuestion implements Question {
    private final questionType type;
    private final String question;
    private final String[] choices;
    private final String answer;

    /**
     * Constructs an AbstractedQuestion with the specified type, subject, question, choices, and answer.
     *
     * @param type the type of the question
     * @param question the question text
     * @param choices the possible choices for the question
     * @param answer the correct answer for the question
     * @throws IllegalArgumentException if any of the parameters are null
     */
    protected AbstractQuestion(questionType type, String question, String[] choices, String answer) {
        if (type == null || question == null || choices == null || answer == null) {
            throw new IllegalArgumentException("The question parameters cannot be null.");
        }
        this.type = type;
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }

    /**
     * Returns the type of question.
     *
     * @return the type of question
     */
    public questionType getType() {
        return type;
    }

    /**
     * Returns the text of the question.
     *
     * @return the text of this question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns the possible choices for the question.
     *
     * @return an array of possible choices for this question
     */
    public String[] getChoices() {
        return choices;
    }

    // new method no javadoc

    public String getAnswer() {
        return answer;
    }

    /**
     * Checks if the given answer is correct.
     *
     * @param answer the answer to check
     * @return true if the given answer is correct, false otherwise
     */

    public boolean correctAnswer(String answer) {
        return answer.equalsIgnoreCase(this.answer);
    }

    /**
     * Compares this question to the object input. The result is true if and only if the argument is not
     * null and is an AbstractedQuestion object that represents the same question as this object.
     *
     * @param input the object to compare this AbstractedQuestion
     * @return true if the given object represents an AbstractedQuestion equivalent to this question,
     * false otherwise
     */
    @Override
    public boolean equals(Object input) {
        if (this == input) {
            return true;
        }
        if (input == null || getClass() != input.getClass()) {
            return false;
        }
        AbstractQuestion that = (AbstractQuestion) input;
        return type == that.type &&
                question.equals(that.question) &&
                answer.equals(that.answer) &&
                java.util.Arrays.equals(choices, that.choices);
    }

    /**
     * Returns a hash code value for the question.
     *
     * @return a hash code value for the question
     */
    @Override
    public int hashCode() {
        int result = java.util.Objects.hash(type, question, answer);
        result = 31 * result + java.util.Arrays.hashCode(choices);
        return result;
    }
}