package model;

import model.AbstractQuestion;

public class QuestionTrueFalse extends AbstractQuestion {

    public QuestionTrueFalse(String question, String[] choices, String answer) {
        super(questionType.trueFalse, question, choices, answer);
    }

}
