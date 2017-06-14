package question.exemple.nico.questionboard;

import java.io.Serializable;

/**
 * Created by nico on 5/2/17.
 */

public class QuestionBoard implements Serializable {

    private long id;
    private String question;
    private String response;
    private Category category;


    private Boolean isResponse = false;

    public void setResponse(Boolean response) {
        isResponse = response;
    }

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
        return category.toString();
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        if(isResponse) {
            return this.getQuestion() + " // " + this.getResponse();
        } else {
            return this.getQuestion();
        }
    }
}
