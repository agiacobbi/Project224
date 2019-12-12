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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;

public class TabbedPaneController {
    private TabbedPaneView view;

    public TabbedPaneController() {
        view = new TabbedPaneView();

        updateLists();
        addListeners();
        addWindowAdapter();

    }

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

    private void addListeners() {
        view.createSurveyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String surveyTitle = JOptionPane.showInputDialog(view,
                        "Enter a title for the new survey: ",
                        "Enter Title",
                        JOptionPane.INFORMATION_MESSAGE
                        );

                if (surveyTitle.length() == 0) {
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
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String selectedSurvey = view.surveysList.getSelectedValue();
                SurveyDatabaseHelper getHelper = new SurveyDatabaseHelper(selectedSurvey);
                Survey s = getHelper.getSurvey();
                getHelper.closeConnection();

                new TakeSurveyController(s);
            }
        });


        view.viewResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String selectedSurvey = view.availableResultsList.getSelectedValue();
                new SurveyResultController(selectedSurvey);
            }
        });
    }

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


    public static void main(String[] args) {
//        ResponseDatabaseHelper deleteHelper = new ResponseDatabaseHelper();
//        SurveyDatabaseHelper deletionHelper = new SurveyDatabaseHelper(null);
//
//        deleteHelper.clearTable();
//        deletionHelper.clearTable();
//
//        deleteHelper.closeConnection();
//        deletionHelper.closeConnection();
        new TabbedPaneController();

    }
}
