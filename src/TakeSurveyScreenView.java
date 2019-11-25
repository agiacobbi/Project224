import javax.swing.*;
import java.awt.*;

public class TakeSurveyScreenView extends JFrame{
    //private Controller controller;
    private JPanel questionBank;
    private Survey survey;
    private int size;


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
        questionBank = (JPanel) getContentPane();
        questionBank.setLayout(new GridLayout(0, 1));
        questionBank.setBackground(Color.CYAN);

        int i = 1;
        for (Question q : survey.getQuestions()) {
            JPanel questionPanel = new JPanel();
            questionPanel.setBackground(Color.CYAN);
            questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
            questionPanel.setBorder(BorderFactory.createTitledBorder("Question" + i));
            JLabel questionLabel = new JLabel(q.getText());

            JRadioButton trueButton = new JRadioButton(q.getTrueResponse());
            JRadioButton falseButton = new JRadioButton(q.getFalseResponse());

            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(trueButton);
            buttonGroup.add(falseButton);

            questionPanel.add(questionLabel);
            questionPanel.add(trueButton);
            questionPanel.add(falseButton);
            questionBank.add(questionPanel);

            i++;
        }

    }

    private void makeButtonGroup() {
        JRadioButton trueButton = new JRadioButton("Yes");
        JRadioButton falseButton = new JRadioButton("No");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(trueButton);
        buttonGroup.add(falseButton);

    }

    public static void main(String[] args) {
        Survey model = new Survey("Survey");
        for (int i = 0; i < 5; i++) {
            Question newGuy = new Question("is this " + i, "Yes", "No", "TF");
            model.addQuestion(newGuy);
        }

        new TakeSurveyScreenView(model);
        //Controller controller = new Controller(model);
    }
}
