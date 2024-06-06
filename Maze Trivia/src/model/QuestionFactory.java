package model;

/*
public QuestionFactory(questionType type, String[] subject, String question, String[] choices, String answer) {
    super(type, subject, question, choices, answer);
}
*/

public class QuestionFactory {
    public static Question createQuestion(String question, String[] choices, String answer, String type) {
        return switch (type) {
            case "multipleChoice" -> new QuestionMultipleChoice(question, choices, answer) {};
            case "trueFalse" -> new QuestionShortAnswer(question, choices, answer) {};
            case "shortAnswer" -> new QuestionTrueFalse(question, choices, answer) {};
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
