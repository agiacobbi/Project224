/**
 * Handles all the action events and the logic for the tabbed pane GUI
 * CPSC 224-01, Fall 2019
 * Final Project -- Poll-A-Bear
 * CITATIONS
 *
 * @author Alex Giacobbi, Ghar Pautz, Win Todd
 * @version v1.0 12/12/19
 */


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;

/**
 * handles all actions involved with the tabbed panes
 * @see TakeSurveyView
 */
public class TabbedPaneController {
    private TabbedPaneView view;

    /**
     *Constructor for TabbedPaneController and handles all actions performed regarding the tabbed panes
     */
    public TabbedPaneController() {
        view = new TabbedPaneView();

        updateLists();
        addListeners();
        addWindowAdapter();

    }

    /**
     * handles all actionListeners involved with switching tabs
     */
    private void addWindowAdapter() {
        view.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent windowEvent) {
                updateLists();
            }

            @Override
            public void windowLostFocus(WindowEvent windowEvent) {

            }
        });
    }

    /**
     * handles actions from JButtons
     */
    private void addListeners() {
        view.createSurveyButton.addActionListener(new ActionListener() {
            /**
             * creates prompt for new survey title and creates new survey
             * @param actionEvent new JOptionPane to name new survey
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String surveyTitle = JOptionPane.showInputDialog(view,
                        "Enter a title for the new survey: ",
                        "Enter Title",
                        JOptionPane.INFORMATION_MESSAGE
                        );

                if (surveyTitle == null) {
                    return;
                }

                SurveyDatabaseHelper checkHelper = new SurveyDatabaseHelper(surveyTitle);
                Survey existingSurvey = checkHelper.getSurvey();
                checkHelper.closeConnection();

                if (existingSurvey.questions.size() > 0) {
                    JOptionPane.showMessageDialog(view,
                            "A survey with this title already exists, please choose a different title",
                            "Cannot create survey",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    new BuildSurveyController(existingSurvey);
                }
            }
        });

        view.deleteSurveyButton.addActionListener(new ActionListener() {
            /**
             * deletes survey
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String selectedSurvey = view.createSurveyList.getSelectedValue();

                SurveyDatabaseHelper getHelper = new SurveyDatabaseHelper(selectedSurvey);
                ResponseDatabaseHelper deleteHelper = new ResponseDatabaseHelper();

                getHelper.deleteSurvey(selectedSurvey);
                getHelper.closeConnection();

                deleteHelper.deleteSurvey(selectedSurvey);
                deleteHelper.closeConnection();

                updateLists();
            }
        });


        view.takeSurveyButton.addActionListener(new ActionListener() {
            /**
             * calls for selected survey to be constructed on take survey screen
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (view.surveysList.isSelectionEmpty()) {
                    return;
                }

                String selectedSurvey = view.surveysList.getSelectedValue();
                SurveyDatabaseHelper getHelper = new SurveyDatabaseHelper(selectedSurvey);
                Survey s = getHelper.getSurvey();
                getHelper.closeConnection();

                new TakeSurveyController(s);
            }
        });


        view.viewResultsButton.addActionListener(new ActionListener() {
            /**
             * opens new JFrame that shows survey responses
             * @param actionEvent
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (view.availableResultsList.isSelectionEmpty()) {
                    return;
                }

                String selectedSurvey = view.availableResultsList.getSelectedValue();
                new SurveyResultController(selectedSurvey);
            }
        });
    }

    /**
     * updates survey lists with correct information
     */
    private void updateLists() {
        SurveyDatabaseHelper surveyHelper = new SurveyDatabaseHelper(null);
        ResponseDatabaseHelper responseHelper = new ResponseDatabaseHelper();
        List<String> createdSurveys = surveyHelper.getAvailableSurveys();
        List<String> takenSurveys = responseHelper.getAvailableSurveys();

        System.out.println(createdSurveys);
        System.out.println(takenSurveys);

        DefaultListModel<String> cSurveyList = new DefaultListModel<String>();
        for (String s : createdSurveys) {
            cSurveyList.addElement(s);
        }
        view.surveysList.setModel(cSurveyList);
        view.createSurveyList.setModel(cSurveyList);

        DefaultListModel<String> tSurveyList = new DefaultListModel<String>();
        for (String s : takenSurveys) {
            tSurveyList.addElement(s);
        }
        view.availableResultsList.setModel(tSurveyList);

        surveyHelper.closeConnection();
        responseHelper.closeConnection();
    }


    /**
     * Runs the program
     * @param args
     */
    public static void main(String[] args) {
        new TabbedPaneController();
    }
}
