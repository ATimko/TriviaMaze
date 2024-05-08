public class Questions {
    //wilasfjadfjqerq
    private String question;
    private String[] options;
    private String correctAnswer;
    private String type;

    public void Question(String question, String[] options, String correctAnswer, String type) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getType() {
        return type;
    }
}