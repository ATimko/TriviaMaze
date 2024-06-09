package model;

import java.io.Serializable;

public interface Question extends Serializable {
    enum QUESTIONTYPE {
        multipleChoice, trueFalse, shortAnswer
    }
    QUESTIONTYPE getType();
    String getQuestion();
    String[] getChoices();
    String getAnswer();
}