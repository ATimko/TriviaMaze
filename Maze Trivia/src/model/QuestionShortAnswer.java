package model;

public class QuestionShortAnswer extends AbstractQuestion {

    public QuestionShortAnswer(String question, String[] choices, String answer) {
        super(questionType.shortAnswer, question, choices, answer);
    }

}
