import javax.xml.crypto.Data;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Question question1 = new Question("Do you have a dog?", "Yes", "No");
        Question question2 = new Question("Do you have a cat?", "Yes", "No");
        Question question3 = new Question("Do you have a fish?", "Yes", "No");
        Question question4 = new Question("Do you prefer dogs or cats?", "Yes", "No");

        Survey survey = new Survey("PetSurvey");
        survey.addQuestion(question1);
        survey.addQuestion(question2);
        survey.addQuestion(question3);
        survey.addQuestion(question4);

        DatabaseHelper databaseHelper = new DatabaseHelper(survey.getTitle(), survey.getQuestions());

        System.out.println(survey.getQuestions());

        List<Question> questions = survey.getQuestions();
        for (Question q : questions) {
            System.out.println(q);
            System.out.println("a. " + q.getTrueResponse());
            System.out.println("b. " + q.getFalseResponse());
            q.getValidResponse();
        }

        databaseHelper.insertResponse(questions);


    }


}
