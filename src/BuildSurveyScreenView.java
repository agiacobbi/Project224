import javax.swing.*;
import java.awt.*;

public class BuildSurveyScreenView extends JFrame {
    private Controller controller;
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

    public BuildSurveyScreenView(Controller controller) {
        super("Build Survey");
        this.controller = controller;
        i = 1;

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

        Controller.MyRadioButtonItemListener myItemListener = controller.new MyRadioButtonItemListener();

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

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}
