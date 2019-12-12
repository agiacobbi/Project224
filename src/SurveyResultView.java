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
import java.awt.*;

public class SurveyResultView extends JFrame {
    JScrollPane graphScroll;
    JPanel graphPanel;
    private String surveyTitle;

    public SurveyResultView(String surveyTitle) {
        this.surveyTitle = surveyTitle;

        setVisible(true);
        setPreferredSize(new Dimension(600, 600));

        setupUI();
        pack();
    }

    private void setupUI() {
        JPanel mainPanel = (JPanel) getContentPane();
        JLabel titleLabel = new JLabel("Results for " + surveyTitle);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        graphScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainPanel.add(graphScroll, BorderLayout.CENTER);

        graphPanel = new JPanel();
        graphPanel.setLayout(new BoxLayout(graphPanel, BoxLayout.Y_AXIS));
        graphScroll.setViewportView(graphPanel);
    }
}
