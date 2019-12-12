/**
 * This is the view for a survey result. It is set up to display the
 * survey title at the top and generates a scrollpane for charts to
 * be added to
 * CPSC 224-01, Fall 2019
 * Final Project -- Poll-A-Bear
 * CITATIONS
 *
 * @author Alex Giacobbi, Ghar Pautz, Win Todd
 * @version v1.0 12/12/19
 */

import javax.swing.*;
import java.awt.*;

/**
 * This class is the view for viewing a survey result. It contains fields
 * for a scrollpane, a graph panel and a string title of the survey. The
 * survey title is displayed at the top of the screen and the graph panel is
 * initialized as an empty panel that the controller can populate.
 */
public class SurveyResultView extends JFrame {
    JScrollPane graphScroll;
    JPanel graphPanel;
    private String surveyTitle;

    /**
     * Initializes the title field to the specified title, sets up the UI
     * @param surveyTitle string survey title to display
     */
    public SurveyResultView(String surveyTitle) {
        this.surveyTitle = surveyTitle;

        setVisible(true);
        setPreferredSize(new Dimension(600, 600));

        setupUI();
        pack();
    }

    /**
     * Sets up the UI- initializes all swing components and sets layouts as necessary
     */
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
