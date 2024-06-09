package model;

public class QuestionShortAnswer extends AbstractQuestion {

    public QuestionShortAnswer(final String theQuestion, final String[] theChoices, final String theAnswer) {
        super(QUESTIONTYPE.shortAnswer, theQuestion, theChoices, theAnswer);
    }

}
