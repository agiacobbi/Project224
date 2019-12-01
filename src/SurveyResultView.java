import javax.swing.*;
import java.awt.*;

public class SurveyResultView extends JFrame {
    JPanel graphPanel;
    String surveyTitle;

    public SurveyResultView(String surveyTitle) {
        this.surveyTitle = surveyTitle;

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));

        setupUI();
        pack();
    }

    private void setupUI() {
        graphPanel = (JPanel) getContentPane();
        JLabel titleLabel = new JLabel("Results for " + surveyTitle);
        graphPanel.add(titleLabel, BorderLayout.NORTH);
    }
}
