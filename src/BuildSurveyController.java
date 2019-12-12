import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BuildSurveyController {
    private BuildSurveyScreenView view;
    private Survey model;
    private ResponseDatabaseHelper database;

    public BuildSurveyController(Survey model) {
        this.view = new BuildSurveyScreenView(this);
        this.model = model;

        view.addQuestionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(!view.writeQuestion.getText().isEmpty()) {
                    if((view.option1.isEnabled() && view.option1.getText().isEmpty()) ||
                            (view.option2.isEnabled() && view.option2.getText().isEmpty()) ||
                            (view.option3.isEnabled() && view.option3.getText().isEmpty()) ||
                            (view.option4.isEnabled() && view.option4.getText().isEmpty())) {
                        JOptionPane.showMessageDialog(null, "Must Fill Out Chosen Responses");
                    }
                    else {
                        JPanel panel = new JPanel();
                        panel.setBackground(Color.CYAN);
                        panel.setLayout(new GridLayout(2, 1));

//                        JLabel textLabel = new JLabel();
//                        Question question = new Question(view.writeQuestion.getText(), "True", "False", "TF");
//                        model.addQuestion(question);
//                        textLabel.setText(view.getI() + ". " + view.writeQuestion.getText());

                        JLabel textLabel = new JLabel();
                        String questionStr = view.writeQuestion.getText();
                        String aResp = view.option1.getText();
                        String bResp = view.option2.getText();
                        String cResp = view.option3.getText().length() > 0 ? view.option3.getText() : null;
                        String dResp = view.option4.getText().length() > 0 ? view.option4.getText() : null;

                        Question question = new Question(questionStr, aResp, bResp, cResp, dResp);
                        model.addQuestion(question);
                        textLabel.setText(view.getI() + ". " + view.writeQuestion.getText());

                        JLabel typeLabel = new JLabel();
                        typeLabel.setFont(new Font("Arial", Font.ITALIC, 12));

                        //Load typeLabel string
                        String responses = new String("1. " + view.option1.getText() + " 2. " + view.option2.getText());
                        if(view.option3.isEnabled()) {
                            responses += " 3. " + view.option3.getText();
                        }
                        if(view.option4.isEnabled()) {
                            responses += " 4. " + view.option4.getText();
                        }
                        typeLabel.setText(responses);

                        panel.add(textLabel);
                        panel.add(typeLabel);
                        view.addedQuestionsPanel.add(panel);
                        view.setI(view.i + 1);
                        view.numberedQuestion.setText(view.getI() + ".");

                        view.writeQuestion.setText("");
                        view.option1.setText("");
                        view.option2.setText("");
                        view.option3.setText("");
                        view.option4.setText("");

                        view.addedQuestionsPanel.validate();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "No Question Given");
                }
            }
        });

        view.twoAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.option1.setEnabled(true);
                view.option2.setEnabled(true);
                view.option3.setEnabled(false);
                view.option4.setEnabled(false);
            }
        });

        view.threeAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.option1.setEnabled(true);
                view.option2.setEnabled(true);
                view.option3.setEnabled(true);
                view.option4.setEnabled(false);
            }
        });

        view.fourAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.option1.setEnabled(true);
                view.option2.setEnabled(true);
                view.option3.setEnabled(true);
                view.option4.setEnabled(true);
            }
        });

        view.buildButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SurveyDatabaseHelper helper = new SurveyDatabaseHelper(model.getTitle());
                for (Question q : model.getQuestions()) {
                    helper.insertQuestion(q);
                }
                view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
                helper.closeConnection();
            }
        });
    }

    public static void main(String[] args) {
        Survey model = new Survey("Survey");
        BuildSurveyController controller = new BuildSurveyController(model);

    }

    class MyRadioButtonItemListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            // https://docs.oracle.com/javase/7/docs/api/java/awt/event/ItemEvent.html
            JRadioButton radioButton = (JRadioButton) e.getSource();
            System.out.println(radioButton.isSelected());
            switch (e.getStateChange()) {
                case ItemEvent.SELECTED:
                    System.out.println(radioButton.getText() + " is now selected");
                    break;
                case ItemEvent.DESELECTED:
                    System.out.println(radioButton.getText() + " is now deselected");
                    break;
            }
        }
    }
}
