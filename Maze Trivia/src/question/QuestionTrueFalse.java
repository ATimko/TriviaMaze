package question;

public class QuestionTrueFalse extends AbstractedQuestion {

    public QuestionTrueFalse(String[] subject, String question, String[] choices, String answer) {
        super(questionType.trueFalse, subject, question, choices, answer);
    }

}
