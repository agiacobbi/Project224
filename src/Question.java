import java.util.Scanner;

public class Question {
    private String text;
    private String trueResponse;
    private String falseResponse;
    private String userResponse;
    private String questionType;


    public Question(String text, String trueResponse, String falseResponse, String type) {
        this.text = text;
        this.trueResponse = trueResponse;
        this.falseResponse = falseResponse;
        this.userResponse = null;
        this.questionType = type;
    }

    public Question() {
        text = "How many dogs do you have?";
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTrueResponse() {
        return trueResponse;
    }

    public void setTrueResponse(String trueResponse) {
        this.trueResponse = trueResponse;
    }

    public String getFalseResponse() {
        return falseResponse;
    }

    public void setFalseResponse(String falseResponse) {
        this.falseResponse = falseResponse;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(String userResponse) {
        this.userResponse = userResponse;
    }

    public void getValidResponse() {
        Scanner kb = new Scanner(System.in);
        userResponse = kb.next();
        System.out.println(userResponse);
    }

    @Override
    public String toString() {
        return "Question: " + text;
    }
}
