package model;

import java.io.Serializable;

public interface Question extends Serializable {
    enum questionType {
        multipleChoice, trueFalse, shortAnswer
    }
    questionType getType();
    String getQuestion();
    String[] getChoices();
    String getAnswer();
}