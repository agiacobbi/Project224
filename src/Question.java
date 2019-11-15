public class Question {
    private String text;
    private String questionReturnType;

    public Question(String text, String questionReturnType) {
        this.text = text;
        this.questionReturnType = questionReturnType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getQuestionReturnType() {
        return questionReturnType;
    }

    public void setQuestionReturnType(String questionReturnType) {
        this.questionReturnType = questionReturnType;
    }
}
