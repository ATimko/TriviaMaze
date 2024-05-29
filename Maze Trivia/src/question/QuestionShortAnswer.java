package question;

public class QuestionShortAnswer extends AbstractedQuestion {

    public QuestionShortAnswer(String[] subject, String question, String[] choices, String answer) {
        super(questionType.shortAnswer, subject, question, choices, answer);
    }

}
