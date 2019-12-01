
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultController {
    ResultSet surveyResults;
    String title;
    SurveyResultView view;

    public ResultController(String title) {
        this.title = title;
        this.view = new SurveyResultView(title);

        getResults();

    }

    private void getResults() {
        DatabaseHelper helper = new DatabaseHelper(title);
        surveyResults = helper.getResponses();


        try {
            int numberOfQuestions = surveyResults.getMetaData().getColumnCount();
            List<Map<String, Integer>> questionResponses = new ArrayList<>();
            for (int i = 0; i < numberOfQuestions - 1; i++) {
                questionResponses.add(new HashMap<>());
            }

            while (surveyResults.next()) {
                for (int i = 0; i < numberOfQuestions - 1; i++) {
                    String key = surveyResults.getString("Q" + (i + 1));
                    if (questionResponses.get(i).containsKey(key)) {
                        questionResponses.get(i).put(key, questionResponses.get(i).get(key) + 1);
                    } else {
                        questionResponses.get(i).put(key, 1);
                    }
                }
            }

            addCharts(questionResponses);

        } catch (SQLException e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Could not load charts.");
            view.graphPanel.add(errorLabel, BorderLayout.CENTER);
        }


    }

    private void addCharts(List<Map<String, Integer>> questionResponses) {
        int i = 1;
        for (Map<String, Integer> responseMap : questionResponses) {
            PieChart chart = new PieChartBuilder()
                    .width(300)
                    .height(300)
                    .title("Question " + i)
                    .build();

            for (String key : responseMap.keySet()) {
                chart.addSeries(key, responseMap.get(key));
            }

            new SwingWrapper<PieChart>(chart).displayChart();
            i++;
        }
    }

    public static void main(String[] args) {
        new ResultController("PetSurvey");
    }
}
