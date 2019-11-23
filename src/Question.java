import java.util.Scanner;

public class Question {
    private String text;
    private String trueResponse;
    private String falseResponse;
    private String userResponse;


    public Question(String text, String trueResponse, String falseResponse) {
        this.text = text;
        this.trueResponse = trueResponse;
        this.falseResponse = falseResponse;
        this.userResponse = null;
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
