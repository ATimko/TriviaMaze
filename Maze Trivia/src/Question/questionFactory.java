package Question;

public class questionFactory extends abstractedQuestion {
    public questionFactory(questionType type, String[] subject, String question, String[] choices, String answer) {
        super(type, subject, question, choices, answer);
    }

    public static question createQuestion(String question, String[] topics, String[] choices, String answer, String type) {
        return switch (type) {
            case "multipleChoice" -> new abstractedQuestion(questionType.multipleChoice, topics, question, choices, answer) {};
            case "trueFalse" -> new abstractedQuestion(questionType.trueFalse, topics, question, new String[]{"True", "False"}, answer) {};
            case "shortAnswer" -> new abstractedQuestion(questionType.shortAnswer, topics, question, new String[0], answer) {};
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
