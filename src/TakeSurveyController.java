/**
 * Handles all the action events and the logic for the take survey screen
 * CPSC 224-01, Fall 2019
 * Final Project -- Poll-A-Bear
 * CITATIONS
 *
 * @author Alex Giacobbi, Ghar Pautz, Win Todd
 * @version v1.0 12/12/19
 */

import javax.swing.*;
import java.awt.event.*;

/**
 * handles all actions the user performs in the take survey screen
 * @see TakeSurveyView
 * @see ResponseDatabaseHelper
 * @see Survey
 */
public class TakeSurveyController {
    private TakeSurveyView view;
    private ResponseDatabaseHelper helper;
    private Survey survey;

    /**
     * Constructor for TakeSurveyController controls all actions and user inputs for the take survey screen
     *
     * @param survey holds the survey that the user will interact with
     */
    public TakeSurveyController(Survey survey) {
        this.view = new TakeSurveyView(survey);
        this.survey = survey;

        addActionListeners();
    }

    /**
     * handles all the actionListener events for the take survey screen
     */
    private void addActionListeners() {
        view.submitResponses.addActionListener(new ActionListener() {
            /**
             * sends survey responses to database
             * @param actionEvent
             */
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
                    /**
                     * holds information for which question response is selected
                     * @param itemEvent
                     */
                    @Override
                    public void itemStateChanged(ItemEvent itemEvent) {
                        JRadioButton button = (JRadioButton) itemEvent.getSource();
                        survey.questions.get(p.questionIndex).setUserResponse(button.getText());
                    }
                });
            }
            if (p.bButton != null) {
                p.bButton.addItemListener(new ItemListener() {
                    /**
                     * holds information for which question response is selected
                     * @param itemEvent
                     */
                    @Override
                    public void itemStateChanged(ItemEvent itemEvent) {
                        JRadioButton button = (JRadioButton) itemEvent.getSource();
                        survey.questions.get(p.questionIndex).setUserResponse(button.getText());
                    }
                });
            }
            if (p.cButton != null) {
                p.cButton.addItemListener(new ItemListener() {
                    /**
                     * holds information for which question response is selected
                     * @param itemEvent
                     */
                    @Override
                    public void itemStateChanged(ItemEvent itemEvent) {
                        JRadioButton button = (JRadioButton) itemEvent.getSource();
                        survey.questions.get(p.questionIndex).setUserResponse(button.getText());
                    }
                });
            }
            if (p.dButton != null) {
                p.dButton.addItemListener(new ItemListener() {
                    /**
                     * holds information for which question response is selected
                     * @param itemEvent
                     */
                    @Override
                    public void itemStateChanged(ItemEvent itemEvent) {
                        JRadioButton button = (JRadioButton) itemEvent.getSource();
                        survey.questions.get(p.questionIndex).setUserResponse(button.getText());
                    }
                });
            }
        }
    }

    /**
     * checks to make sure that all questions are answered before the survey can be submitted
     *
     * @return true/false depending on state of survey
     */
    private boolean allQuestionsAnswered() {
        for (Question q : survey.questions) {
            if (q.getUserResponse() == null) {
                return false;
            }
        }
        return true;
    }
}
