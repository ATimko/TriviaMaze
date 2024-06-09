package model;

public class QuestionMultipleChoice extends AbstractQuestion {

    public QuestionMultipleChoice(final String theQuestion, final String[] theChoices, final String theAnswer) {
        super(QUESTIONTYPE.multipleChoice, theQuestion, theChoices, theAnswer);
    }

}
