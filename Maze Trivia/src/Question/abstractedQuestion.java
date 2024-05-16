package Question;

public class abstractedQuestion implements question {
    private final questionType type;
    private final String question;
    private final String[] choices;
    private final String answer;
    private final String[] subject;

    protected abstractedQuestion(questionType type, String[] subject, String question, String[] choices, String answer) {
        if (type == null || subject == null || question == null || choices == null || answer == null) {
            throw new IllegalArgumentException("You cannot have null Question parameters!");
        }

        this.type = type;
        this.question = question;
        this.choices = choices;
        this.answer = answer;
        this.subject = subject;
    }

    public questionType getType() {
        return type;
    }

    public String[] getSubjects() {
        return subject;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getChoices() {
        return choices;
    }

    public boolean correctAnswer(String answer) {
        return answer.equalsIgnoreCase(this.answer);
    }

    @Override
    public boolean equals(Object input) {
        // write something that checks whether the selected answer matches the correct answer
        return true;
    }
}
