/**
 * DESCRIPTION
 * CPSC 224-01, Fall 2019
 * Final Project -- Poll-A-Bear
 * CITATIONS
 *
 * @author Alex Giacobbi, Ghar Pautz, Win Todd
 * @version v1.0 12/12/19
 */

import javax.swing.*;
import java.awt.event.*;

public class TakeSurveyController {
    private TakeSurveyView view;
    private ResponseDatabaseHelper helper;
    private Survey survey;

    public TakeSurveyController(Survey survey) {
        this.view = new TakeSurveyView(survey);
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

        for (TakeSurveyView.QuestionPanel p : view.questions) {
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
