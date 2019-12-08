import javax.swing.*;
import java.awt.*;

public class SurveyResultView extends JFrame {
    JPanel mainPanel;
    JScrollPane graphScroll;
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
        mainPanel = (JPanel) getContentPane();
        JLabel titleLabel = new JLabel("Results for " + surveyTitle);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        graphScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(graphScroll, BorderLayout.CENTER);

        graphPanel = new JPanel();
        graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.Y_AXIS));
        graphScroll.setViewportView(graphPanel);
    }
}
