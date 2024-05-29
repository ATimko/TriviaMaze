package question;

public class QuestionMultipleChoice extends AbstractedQuestion {
    private String[] choices;

    public QuestionMultipleChoice(int id, String subject, String question, String[] choices, String answer) {
        super(subject, question, answer);
        this.choices = choices;
    }

    // Getters and setters

    public String[] getChoices() {
        return choices;
    }
}
