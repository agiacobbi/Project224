import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.util.List;
import java.util.ArrayList;

public class TabbedPaneController {
    TabbedPaneView view;

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

        view.editSurveyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                // TODO maybe implement edit survey functionality

            }
        });


        view.takeSurveyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String selectedSurvey = view.surveysList.getSelectedValue();
                SurveyDatabaseHelper getHelper = new SurveyDatabaseHelper(selectedSurvey);
                Survey s = getHelper.getSurvey();
                getHelper.closeConnection();

                new TakeSurveyScreenController(s);
            }
        });


        view.viewResultsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                // TODO add listener to view results for a selected survey
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
        new TabbedPaneController();
    }
}
