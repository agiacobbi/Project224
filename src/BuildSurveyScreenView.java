import javax.swing.*;
import java.awt.*;

public class BuildSurveyScreenView extends JFrame {
    protected JLabel numberedQuestion;
    protected JTextField writeQuestion;
    protected JButton addQuestionButton;
    protected JButton numericalButton;
    protected JButton trueFalseButton;
    protected JButton yesNoButton;
    protected JButton buildButton;

    public BuildSurveyScreenView() {
        super("Build Survey");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 850));
        setupUI();
        pack();
    }

    private void setupUI() {

        JPanel mainPanel = (JPanel) getContentPane();
        mainPanel.setBackground(Color.CYAN);
        mainPanel.setLayout(new BorderLayout());

        /*
        ~~~~~~~~~~~~~~~~~~~~~~~~~
        mainPanel's NORTH
        ~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        JPanel newQuestionPanel = new JPanel();
        newQuestionPanel.setBorder(BorderFactory.createTitledBorder("What is your question?"));
        newQuestionPanel.setLayout(new GridLayout(2, 0));


        /*
        ~~~~~~~~~~~~~~~~~~~~~~~~~
        Row 1 of newQuestionPanel
        ~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        JPanel addQuestionPanel = new JPanel();
        addQuestionPanel.setLayout(new BorderLayout());

        numberedQuestion = new JLabel("1.");
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

        numericalButton = new JButton("Numerical");
        trueFalseButton = new JButton("True/False");
        yesNoButton = new JButton("Yes/No");

        questionReturnTypePanel.add(numericalButton);
        questionReturnTypePanel.add(trueFalseButton);
        questionReturnTypePanel.add(yesNoButton);


        newQuestionPanel.add(questionReturnTypePanel);
        newQuestionPanel.setBackground(Color.CYAN);

       mainPanel.add(newQuestionPanel, BorderLayout.NORTH);




        /*
        ~~~~~~~~~~~~~~~~~~~~~~~~~
        mainPanel's CENTER
        ~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        JPanel addedQuestionsPanel = new JPanel();
        addedQuestionsPanel.setLayout(new GridLayout(10, 1));
        addedQuestionsPanel.setBackground(Color.CYAN);

        mainPanel.add(addedQuestionsPanel, BorderLayout.CENTER);



        /*
        ~~~~~~~~~~~~~~~~~~~~~~~~~
        mainPanel's SOUTH
        ~~~~~~~~~~~~~~~~~~~~~~~~~
         */
        buildButton = new JButton("Build Survey");
        mainPanel.add(buildButton, BorderLayout.SOUTH);
    }
}
