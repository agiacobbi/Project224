/**
 * Creates the GUI for the view screen that is created when a user takes a survey
 * CPSC 224-01, Fall 2019
 * Final Project -- Poll-A-Bear
 * CITATIONS
 *
 * @author Alex Giacobbi, Ghar Pautz, Win Todd
 * @version v1.0 12/12/19
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * sets up the GUI for the take survey screen
 * Extends JFrame and uses BoxLayouts, BorderLayouts, and ScrollPanes to format the view components
 * @see Survey
 */
public class TakeSurveyView extends JFrame{
    JButton submitResponses;
    List<QuestionPanel> questions;
    private Survey survey;

    /**
     * Constructor for the take survey screen
     * @param survey the survey that the user will interact with
     */
    public TakeSurveyView(Survey survey) {
        super("Take Survey");
        this.survey = survey;
        questions = new ArrayList<>();

        setVisible(true);
        setPreferredSize(new Dimension(700, 550));

        setupUI();
        pack();
    }

    /**
     * Formats all the GUI components for the take survey screen
     */
    private void setupUI() {
        JPanel frame = (JPanel) getContentPane();
        JPanel questionBank = new JPanel();
        questionBank.setLayout(new BoxLayout(questionBank, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(questionBank);
        questionBank.setBackground(Color.CYAN);

        int i = 1;
        for (Question q : survey.getQuestions()) {
            QuestionPanel questionPanel = new QuestionPanel(q, i);
            questionBank.add(questionPanel);
            questions.add(questionPanel);
            i++;
        }

        submitResponses = new JButton("Submit Responses");
        frame.add(submitResponses, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * creates a JPanel with all information relevant to a question
     */
    class QuestionPanel extends JPanel {
        Question question;
        int questionIndex;
        JRadioButton aButton;
        JRadioButton bButton;
        JRadioButton cButton;
        JRadioButton dButton;

        /**
         *Adds a question to a panel and holds other relevant information like the question responses
         *
         * @param question question that will be added to the panel
         * @param i question index
         */
        public QuestionPanel(Question question, int i) {
            this.question = question;
            questionIndex = i - 1;

            setLayout(new GridLayout(0,1));
            setBackground(Color.CYAN);
            setBorder(BorderFactory.createTitledBorder("Question" + i));

            addComponents();
        }

        /**
         * adds components for each question to the take survey screen
         */
        private void addComponents() {
            JLabel questionLabel = new JLabel(question.getText());
            add(questionLabel);

            ButtonGroup buttonGroup = new ButtonGroup();
            if (question.getaResponse() != null) {
                aButton = new JRadioButton(question.getaResponse());
                System.out.println("a response: " + question.getaResponse());
                add(aButton);
                buttonGroup.add(aButton);
            }
            if (question.getbResponse() != null) {
                bButton = new JRadioButton(question.getbResponse());
                add(bButton);
                buttonGroup.add(bButton);
            }
            if (question.getcResponse() != null) {
                cButton = new JRadioButton(question.getcResponse());
                add(cButton);
                buttonGroup.add(cButton);
            }
            if (question.getdResponse() != null) {
                dButton = new JRadioButton(question.getdResponse());
                add(dButton);
                buttonGroup.add(dButton);
            }
        }
    }
}
