import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TakeSurveyScreenView extends JFrame{
    //private Controller controller;
    JPanel frame;
    private JPanel questionBank;
    private Survey survey;
    private int size;
    public JRadioButton trueButton;
    public JRadioButton falseButton;
    public JButton submitResponses;
    private DatabaseHelper helper;


    /*public TakeSurveyScreenView(Controller controller, Survey survey) {
        super("Take Survey");
        this.controller = controller;
        this.survey = survey;

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 850));
        setupUI();
        pack();
    }*/

    public TakeSurveyScreenView(Survey survey) {
        super("Take Survey");
        this.survey = survey;

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 850));
        setupUI();
        pack();
    }

    private void setupUI() {
        frame = (JPanel) getContentPane();
        //frame.setLayout();
        questionBank = new JPanel();
        questionBank.setLayout(new GridLayout(0, 1));
        JScrollPane scrollPane = new JScrollPane(questionBank);
        questionBank.setBackground(Color.CYAN);

        int i = 1;
        for (Question q : survey.getQuestions()) {
            JPanel questionPanel = new JPanel();
            questionPanel.setBackground(Color.CYAN);
            questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
            questionPanel.setBorder(BorderFactory.createTitledBorder("Question" + i));
            JLabel questionLabel = new JLabel(q.getText());

            trueButton = new JRadioButton(q.getTrueResponse());
            falseButton = new JRadioButton(q.getFalseResponse());

            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(trueButton);
            buttonGroup.add(falseButton);

            trueButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("true");

                    int i = 0;
                    for(int j = 0; j < survey.questions.size(); j++) {
                        if(survey.questions.get(j).getText() == questionLabel.getText()) {
                            i = j;
                        }
                    }
                    survey.questions.get(i).setUserResponse("true");
                }
            });

            falseButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.out.println("false");

                    int i = 0;
                    for(int j = 0; j < survey.questions.size(); j++) {
                        if(survey.questions.get(j).getText() == questionLabel.getText()) {
                            i = j;
                        }
                    }
                    survey.questions.get(i).setUserResponse("false");

                }
            });

            questionPanel.add(questionLabel);
            questionPanel.add(trueButton);
            questionPanel.add(falseButton);
            questionBank.add(questionPanel);

            i++;
        }
        submitResponses = new JButton("Submit Responses");
        frame.add(submitResponses, BorderLayout.SOUTH);
        frame.add(scrollPane, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Survey model = new Survey("Survey");
        for (int i = 0; i < 10; i++) {
            Question newGuy = new Question("is this " + i, "Yes", "No", "TF");
            model.addQuestion(newGuy);
        }

        //new TakeSurveyScreenView(model);
        //Controller controller = new Controller(model);
        new TakeSurveyScreenController(model);
    }
}
