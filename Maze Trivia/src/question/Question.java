package question;

import java.io.Serializable;

public interface Question extends Serializable {
    enum questionType { multipleChoice, trueFalse, shortAnswer }
    questionType getType();
    String getQuestion();
    String[] getChoices();
    String getAnswer();
    boolean correctAnswer(String answer);
}