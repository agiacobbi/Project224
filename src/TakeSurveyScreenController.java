import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TakeSurveyScreenController {
    private TakeSurveyScreenView view;
    private ResponseDatabaseHelper helper;
    private Survey survey;

    public TakeSurveyScreenController(Survey survey) {
        this.view = new TakeSurveyScreenView(survey);
        this.survey = survey;

        addActionListeners();


    }

    private void addActionListeners() {
        view.submitResponses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                helper.insertResponse(survey.questions);
            }
        });
    }


}
