import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
                ResponseDatabaseHelper helper = new ResponseDatabaseHelper();
                helper.insertResponse(survey.questions);
            }
        });

        for (TakeSurveyScreenView.QuestionPanel p : view.questions) {
            if (p.aButton != null) {
                p.aButton.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent itemEvent) {
                        JRadioButton button = (JRadioButton) itemEvent.getSource();
                        if (itemEvent.getStateChange() == itemEvent.SELECTED) {
                            survey.questions.get(p.questionIndex).setUserResponse(button.getText());
                        }
                    }
                });
            }
        }
    }


}
