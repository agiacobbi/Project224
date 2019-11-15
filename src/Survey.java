import java.util.ArrayList;
import java.util.List;

public class Survey {
    List<Question> questions;
    String title;

    public Survey(String title) {
        this.title = title;
        questions = new ArrayList<>();
    }

    public Survey(List<Question> questions, String title) {
        this.questions = questions;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
