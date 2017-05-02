package question.exemple.nico.questionboard;

/**
 * Created by nico on 5/2/17.
 */

public class QuestionBoard {

    private long id;
    private String question;
    private String response;
    private String category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return  ", question='" + question + '\'' +
                ", response='" + response + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
