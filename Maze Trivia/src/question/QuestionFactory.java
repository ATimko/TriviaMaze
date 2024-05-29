package question;

/*
public QuestionFactory(questionType type, String[] subject, String question, String[] choices, String answer) {
    super(type, subject, question, choices, answer);
}
*/

public class QuestionFactory {
    public static Question createQuestion(String question, String[] subject, String[] choices, String answer, String type) {
        return switch (type) {
            case "multipleChoice" -> new QuestionMultipleChoice(subject, question, choices, answer) {};
            case "trueFalse" -> new QuestionShortAnswer(subject, question, choices, answer) {};
            case "shortAnswer" -> new QuestionTrueFalse(subject, question, choices, answer) {};
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
