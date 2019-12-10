import javax.swing.*;
import java.awt.event.*;

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
                if (!allQuestionsAnswered()) {
                    JOptionPane.showMessageDialog(view,
                            "You must answer all the questions",
                            "Cannot submit",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ResponseDatabaseHelper helper = new ResponseDatabaseHelper(survey.title);
                helper.insertResponse(survey.questions);
                helper.closeConnection();

                view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
            }
        });

        for (TakeSurveyScreenView.QuestionPanel p : view.questions) {
            if (p.aButton != null) {
                p.aButton.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent itemEvent) {
                        JRadioButton button = (JRadioButton) itemEvent.getSource();
                        survey.questions.get(p.questionIndex).setUserResponse(button.getText());
                    }
                });
            }
            if (p.bButton != null) {
                p.bButton.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent itemEvent) {
                        JRadioButton button = (JRadioButton) itemEvent.getSource();
                        survey.questions.get(p.questionIndex).setUserResponse(button.getText());
                    }
                });
            }
            if (p.cButton != null) {
                p.cButton.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent itemEvent) {
                        JRadioButton button = (JRadioButton) itemEvent.getSource();
                        survey.questions.get(p.questionIndex).setUserResponse(button.getText());
                    }
                });
            }
            if (p.dButton != null) {
                p.dButton.addItemListener(new ItemListener() {
                    @Override
                    public void itemStateChanged(ItemEvent itemEvent) {
                        JRadioButton button = (JRadioButton) itemEvent.getSource();
                        survey.questions.get(p.questionIndex).setUserResponse(button.getText());
                    }
                });
            }
        }
    }

    private boolean allQuestionsAnswered() {
        for (Question q : survey.questions) {
            if (q.getUserResponse() == null) {
                return false;
            }
        }
        return true;
    }


}
