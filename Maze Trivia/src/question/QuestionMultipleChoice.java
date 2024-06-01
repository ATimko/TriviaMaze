package question;

public class QuestionMultipleChoice extends AbstractQuestion {

    public QuestionMultipleChoice(String question, String[] choices, String answer) {
        super(questionType.multipleChoice, question, choices, answer);
    }

}
