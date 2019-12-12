import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TakeSurveyScreenView extends JFrame{
    //private Controller controller;
    JPanel frame;
    JPanel questionBank;
    JButton submitResponses;
    List<QuestionPanel> questions;
    Survey survey;


    public TakeSurveyScreenView(Survey survey) {
        super("Take Survey");
        this.survey = survey;
        questions = new ArrayList<>();

        setVisible(true);
        setPreferredSize(new Dimension(700, 550));

        setupUI();
        pack();
    }

    private void setupUI() {
        frame = (JPanel) getContentPane();
        questionBank = new JPanel();
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

    class QuestionPanel extends JPanel {
        Question question;
        int questionIndex;
        JRadioButton aButton;
        JRadioButton bButton;
        JRadioButton cButton;
        JRadioButton dButton;

        public QuestionPanel(Question question, int i) {
            this.question = question;
            questionIndex = i - 1;

            setLayout(new GridLayout(0,1));
            setBackground(Color.CYAN);
            setBorder(BorderFactory.createTitledBorder("Question" + i));

            addComponents();
        }

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
