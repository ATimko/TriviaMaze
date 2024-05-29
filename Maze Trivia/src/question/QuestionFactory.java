package question;

import database.DatabaseManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class QuestionFactory {
    private List<QuestionMultipleChoice> multipleChoiceQuestions;
    private List<QuestionTrueFalse> trueFalseQuestions;
    private List<QuestionShortAnswer> shortAnswerQuestions;

    public QuestionFactory() {
        this.multipleChoiceQuestions = new ArrayList<>();
        this.trueFalseQuestions = new ArrayList<>();
        this.shortAnswerQuestions = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        try (Connection connection = DatabaseManager.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM questions")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String type = resultSet.getString("type");
                String subject = resultSet.getString("subject");
                String question = resultSet.getString("question");
                String choices = resultSet.getString("choices");
                String answer = resultSet.getString("answer");

                switch (type.toLowerCase()) {
                    case "multiplechoice":
                        String[] choiceArray = choices.split(",");
                        multipleChoiceQuestions.add(new QuestionMultipleChoice(id, subject, question, choiceArray, answer));
                        break;
                    case "truefalse":
                        trueFalseQuestions.add(new QuestionTrueFalse(id, subject, question, answer));
                        break;
                    case "shortanswer":
                        shortAnswerQuestions.add(new QuestionShortAnswer(id, subject, question, answer));
                        break;
                    default:
                        System.out.println("Unknown question type: " + type);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Getters for the lists

    public List<QuestionMultipleChoice> getMultipleChoiceQuestions() {
        return multipleChoiceQuestions;
    }

    public List<QuestionTrueFalse> getTrueFalseQuestions() {
        return trueFalseQuestions;
    }

    public List<QuestionShortAnswer> getShortAnswerQuestions() {
        return shortAnswerQuestions;
    }
}
