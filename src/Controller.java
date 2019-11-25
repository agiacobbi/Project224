import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {
    private BuildSurveyScreenView view;
    private Survey model;
    private DatabaseHelper database;

    public Controller(Survey model) {
        this.view = new BuildSurveyScreenView(this);
        this.model = model;

        view.trueFalseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel();
                panel.setBackground(Color.CYAN);
                panel.setLayout(new GridLayout(2,1));

                JLabel textLabel = new JLabel();
                Question question = new Question(view.writeQuestion.getText(), "True", "False");
                model.addQuestion(question);
                textLabel.setText(view.getI() + ". " + view.writeQuestion.getText());

                JLabel typeLabel = new JLabel();
                typeLabel.setFont(new Font("Arial", Font.ITALIC, 12));
                typeLabel.setText(view.trueFalseButton.getText());

                panel.add(textLabel);
                panel.add(typeLabel);
                view.addedQuestionsPanel.add(panel);
                view.setI(view.i + 1);
                view.numberedQuestion.setText(view.getI() + ".");


                view.addedQuestionsPanel.validate();

                if (view.getI() > 10) {
                    view.addQuestionButton.setEnabled(false);
                    view.numberedQuestion.setText("");
                }
            }
        });


    }

    public static void main(String[] args) {
        Survey model = new Survey("Survey");
        Controller controller = new Controller(model);

    }
}
