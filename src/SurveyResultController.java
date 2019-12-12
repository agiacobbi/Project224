import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.Styler;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SurveyResultController {
    private String title;
    private SurveyResultView view;

    public SurveyResultController(String title) {
        this.title = title;
        this.view = new SurveyResultView(title);

        getResults();

    }

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