package Question;

import java.io.Serializable;

public interface question extends Serializable {
    enum questionType { multipleChoice, trueFalse, shortAnswer }
    questionType getType();
    String getQuestion();
    String[] getChoices();
    String[] getSubjects();
    boolean correctAnswer(String answer);
}