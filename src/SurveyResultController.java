/**
 * This program controls the result view of the app. It loads results from the
 * database, generates pie charts for each question and displays them to the
 * user
 * CPSC 224-01, Fall 2019
 * Final Project -- Poll-A-Bear
 * CITATIONS
 *
 * @author Alex Giacobbi, Ghar Pautz, Win Todd
 * @version v1.0 12/12/19
 */

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * This class is a controller for the SurveyResultView. It contains fields for the
 * survey title and the view. There are methods to get the results for a survey title,
 * and add XChart pie charts to the view based on the results.
 *
 * @see SurveyResultView
 * @see Question
 * @see Survey
 * @see ResponseDatabaseHelper
 * @see SurveyDatabaseHelper
 */
public class SurveyResultController {
    private String title;
    private SurveyResultView view;

    /**
     * Initializes the title field to the title passed into the constructor,
     * and initializes a new result view. Calls getResults() to load results
     * @param title String title of the survey to be viewed
     */
    public SurveyResultController(String title) {
        this.title = title;
        this.view = new SurveyResultView(title);

        getResults();

    }

    /**
     * Connects to both the Question and Response databases to retrieve results for
     * a survey. Gets the question strings and stores the results for each question in a
     * Map of answer strings and the corresponding number of responses for that possible answer.
     * calls generateCharts to display the results
     */
    private void getResults() {
        ResponseDatabaseHelper helper = new ResponseDatabaseHelper(title);
        SurveyDatabaseHelper surveyHelper = new SurveyDatabaseHelper(title);
        List<Map<String, Integer>> resultsList = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        Survey survey = surveyHelper.getSurvey();
        surveyHelper.closeConnection();

        for (Question q : survey.questions) {
            questionList.add(q);
            resultsList.add(helper.getResponsesForQuestion(q));
            System.out.println("Question: " + q.getText());
            System.out.println(helper.getResponsesForQuestion(q));
        }

        helper.closeConnection();
        addCharts(resultsList, questionList);
    }

    /**
     * Generates pie charts for each question
     * @param questionResponses a list of maps where each element represents a question and the map
     *                          is the map of the recorded responses
     * @param questions a list of questions in the survey
     */
    private void addCharts(List<Map<String, Integer>> questionResponses, List<Question> questions) {
        System.out.println(questionResponses);

        int i = 1;
        for (Map<String, Integer> responseMap : questionResponses) {
            String questionText = questions.get(i - 1).getText();
            PieChart chart = new PieChartBuilder()
                    .width(300)
                    .height(300)
                    .title(questionText)
                    .build();

            chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
            chart.getStyler().setLegendLayout(Styler.LegendLayout.Vertical);
            chart.getStyler().setLegendVisible(true);

            for (String key : responseMap.keySet()) {
                chart.addSeries(key, responseMap.get(key));
            }

            JPanel chartPanel = new XChartPanel<>(chart);
            chartPanel.setBorder(BorderFactory.createTitledBorder("Question " + i));
            view.graphPanel.add(chartPanel);
            i++;
        }
        view.graphScroll.setViewportView(view.graphPanel);
    }
}
