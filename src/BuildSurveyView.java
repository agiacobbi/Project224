/**
 * This class creates the GUI for when the user prompts for a new survey to be created. The design pattern utilizes
 * MVC architecture to separate firing and handling events into different classes.
 *
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
 * View class that creates the GUI of the window that appears when a user chooses to make a new survey.
 * BuildSurveyView extends JFrame, and uses a combination of layouts including BorderLayout and GridLayout,
 * as well as several buttons, text fields, and other GUI components that the Java Swing Framework provides.
 *
 * @see BuildSurveyController
 */
public class BuildSurveyView extends JFrame {
    private BuildSurveyController controller;
    protected JLabel numberedQuestion;
    protected JTextField writeQuestion;
    protected JTextField option1;
    protected JTextField option2;
    protected JTextField option3;
    protected JTextField option4;
    protected JButton addQuestionButton;
    protected JRadioButton twoAnswerButton;
    protected JRadioButton threeAnswerButton;
    protected JRadioButton fourAnswerButton;
    protected JButton buildButton;
    protected JPanel addQuestionPanel;
    protected JPanel addedQuestionsPanel;
    protected JPanel newQuestionPanel;
    protected int i;

    /**
     * Constructor for BuildSurveyView. New JFrame is created with reference to BuildSurveyController object.
     *
     * @param controller controller is BuildSurveyController object that contains a reference to the class that handles
     *                   the events that are fired when BuildSurveyView GUI components get interacted with
     */
    public BuildSurveyView(BuildSurveyController controller) {
        super("Build Survey");
        this.controller = controller;
        i = 1;

        setVisible(true);
        setPreferredSize(new Dimension(700, 550));
        setupUI();
        pack();
    }

    /**
     * The JFrame's components are created and set in place on the GUI for user interaction
     */
    private void setupUI() {

        JPanel mainPanel = (JPanel) getContentPane();
        mainPanel.setBackground(Color.CYAN);
        mainPanel.setLayout(new BorderLayout());

        /*
        ~~~~~~~~~~~~~~~~~~~~~~~~~
        mainPanel's NORTH
        ~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        newQuestionPanel = new JPanel();
        newQuestionPanel.setBorder(BorderFactory.createTitledBorder("What is your question?"));
        newQuestionPanel.setLayout(new GridLayout(0, 1));


        /*
        ~~~~~~~~~~~~~~~~~~~~~~~~~
        Row 1 of newQuestionPanel
        ~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        addQuestionPanel = new JPanel();
        addQuestionPanel.setLayout(new BorderLayout());

        numberedQuestion = new JLabel(i + ".");
        writeQuestion = new JTextField();
        JLabel questionReturnType = new JLabel("What kind of response are you looking for?");
        addQuestionButton = new JButton("Add Question");

        addQuestionPanel.add(numberedQuestion, BorderLayout.WEST);
        addQuestionPanel.add(writeQuestion, BorderLayout.CENTER);
        addQuestionPanel.add(questionReturnType, BorderLayout.SOUTH);
        addQuestionPanel.add(addQuestionButton, BorderLayout.EAST);
        addQuestionPanel.setBackground(Color.CYAN);

        newQuestionPanel.add(addQuestionPanel);


        /*
        ~~~~~~~~~~~~~~~~~~~~~~~~~
        Row 2 of newQuestionPanel
        ~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        JPanel questionReturnTypePanel = new JPanel();
        questionReturnTypePanel.setLayout(new GridLayout(0,4));
        questionReturnTypePanel.setBackground(Color.CYAN);

        BuildSurveyController.MyRadioButtonItemListener myItemListener = controller.new MyRadioButtonItemListener();

        twoAnswerButton = new JRadioButton("2 Answer Options");
        twoAnswerButton.setBackground(Color.CYAN);
        twoAnswerButton.addItemListener(myItemListener);

        threeAnswerButton = new JRadioButton("3 Answer Options");
        threeAnswerButton.setBackground(Color.CYAN);
        threeAnswerButton.addItemListener(myItemListener);

        fourAnswerButton = new JRadioButton("4 Answer Options");
        fourAnswerButton.setBackground(Color.CYAN);
        fourAnswerButton.addItemListener(myItemListener);

        ButtonGroup answerGroup = new ButtonGroup();

        answerGroup.add(twoAnswerButton);
        answerGroup.add(threeAnswerButton);
        answerGroup.add(fourAnswerButton);

        questionReturnTypePanel.add(twoAnswerButton);
        questionReturnTypePanel.add(threeAnswerButton);
        questionReturnTypePanel.add(fourAnswerButton);

        newQuestionPanel.add(questionReturnTypePanel);
        newQuestionPanel.setBackground(Color.CYAN);

        /*
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Third row of newQuestionPanel
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new GridLayout(0,4));

        option1 = new JTextField();
        option2 = new JTextField();
        option3 = new JTextField();
        option4 = new JTextField();

        option1.setEnabled(false);
        option2.setEnabled(false);
        option3.setEnabled(false);
        option4.setEnabled(false);

        optionsPanel.add(option1);
        optionsPanel.add(option2);
        optionsPanel.add(option3);
        optionsPanel.add(option4);

        newQuestionPanel.add(optionsPanel);

       mainPanel.add(newQuestionPanel, BorderLayout.NORTH);


        /*
        ~~~~~~~~~~~~~~~~~~~~~~~~~
        mainPanel's CENTER
        ~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        addedQuestionsPanel = new JPanel();
        addedQuestionsPanel.setLayout(new GridLayout(0, 1));
        addedQuestionsPanel.setBackground(Color.CYAN);
        JScrollPane scrollPane = new JScrollPane(addedQuestionsPanel);

        mainPanel.add(scrollPane, BorderLayout.CENTER);



        /*
        ~~~~~~~~~~~~~~~~~~~~~~~~~
        mainPanel's SOUTH
        ~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        buildButton = new JButton("Build Survey");
        mainPanel.add(buildButton, BorderLayout.SOUTH);
    }

    /**
     * Getter method for retrieving I value, which is counter for number of questions on survey
     *
     * @return the chronological number of the question that is being added to the survey
     */
    public int getI() {
        return i;
    }

    /**
     * Setter method for setting the I value of the number for question on survey
     *
     * @param i the number that is assigned chronologically to the question on the survey
     */
    public void setI(int i) {
        this.i = i;
    }
}
