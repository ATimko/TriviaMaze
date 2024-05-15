package Question;

import java.io.Serializable;

public interface question extends Serializable {
    enum QuestionType { Multiple, TrueFalse, ShortAnswer }

    QuestionType getType();

    String getQuestion();

    String[] getChoices();

    String[] getSubjects();

    //boolean correctAnswer(String answer);
}