import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TakeSurveyScreenController {
    private TakeSurveyScreenView view;
    private DatabaseHelper helper;

    public TakeSurveyScreenController(Survey survey) {
        this.view = new TakeSurveyScreenView(survey);

        view.submitResponses.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                helper.insertResponse(survey.questions);
            }
        });
    }
}
