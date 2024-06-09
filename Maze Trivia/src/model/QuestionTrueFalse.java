package model;

public class QuestionTrueFalse extends AbstractQuestion {

    public QuestionTrueFalse(final String theQuestion, final String[] theChoices, final String theAnswer) {
        super(QUESTIONTYPE.trueFalse, theQuestion, theChoices, theAnswer);
    }

}
