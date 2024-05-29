package question;

public class QuestionFactory {
    public QuestionFactory(Question.questionType type, String[] subject, String question, String[] choices, String answer) {
        super(type, subject, question, choices, answer);
    }

    public static Question createQuestion(String question, String[] topics, String[] choices, String answer, String type) {
        return switch (type) {
            case "multipleChoice" -> new AbstractedQuestion(questionType.multipleChoice, topics, question, choices, answer) {};
            case "trueFalse" -> new AbstractedQuestion(questionType.trueFalse, topics, question, new String[]{"True", "False"}, answer) {};
            case "shortAnswer" -> new AbstractedQuestion(questionType.shortAnswer, topics, question, new String[0], answer) {};
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
