package question;

public class QuestionMultipleChoice extends AbstractQuestion {

    public QuestionMultipleChoice(String[] subject, String question, String[] choices, String answer) {
        super(questionType.multipleChoice, subject, question, choices, answer);
    }

}
