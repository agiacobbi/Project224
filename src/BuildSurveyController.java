/**
 * This class handles the events being fired on the BuildSurveyView window that appears when the user creates a new
 * survey. The design pattern utilizes MVC architecture to separate firing and handling events into different classes.
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
import java.awt.event.*;

/**
 * Controller class that connects the app to an instance of BuildSurveyView when user prompts for a new Survey object to
 * be created. BuildSurveyController handles the events that are fired on the BuildSurveyView GUI.
 *
 * @see BuildSurveyView
 */
public class BuildSurveyController {
    private BuildSurveyView view;

    /**
     * Constructor for BuildSurveyController. A new instance of BuildSurveyController is called with a reference to
     * a Survey object.
     *
     * @param model model is a Survey object that is created from responses from events fired by BuildSurveyView
     *              and handled by BuildSurveyController
     */
    public BuildSurveyController(Survey model) {
        this.view = new BuildSurveyView(this);


        view.addQuestionButton.addActionListener(new ActionListener() {
            /**
             * Handles the events fired by user on the GUI, specifically attached to the addQuestionButton on the
             * BuildSurveyView window that appears when the user is creating a survey
             *
             * @param actionEvent actionEvent is an ActionEvent object that the user creates when they press the button
             *                    on the GUI
             */
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
            /**
             * Handles the event fired by the user on the GUI, specifically when the user selects the radio button
             * for 2 answers to be available for the question being added
             *
             * @param actionEvent actionEvent is an ActionEvent object that the user creates when they press the button
             *                   on the GUI
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.option1.setEnabled(true);
                view.option2.setEnabled(true);
                view.option3.setEnabled(false);
                view.option4.setEnabled(false);
            }
        });

        view.threeAnswerButton.addActionListener(new ActionListener() {
            /**
             * Handles the event fired by the user on the GUI, specifically when the user selects the radio button
             * for 3 answers to be available for the question being added
             *
             * @param actionEvent actionEvent is an ActionEvent object that the user creates when they press the button
             *                    on the GUI
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.option1.setEnabled(true);
                view.option2.setEnabled(true);
                view.option3.setEnabled(true);
                view.option4.setEnabled(false);
            }
        });

        view.fourAnswerButton.addActionListener(new ActionListener() {
            /**
             * Handles the event fired by the user on the GUI, specifically when the user selects the radio button
             * for 4 answers to be available for the question being added
             *
             * @param actionEvent actionEvent is an ActionEvent object that the user creates when they press the button
             *                    on the GUI
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                view.option1.setEnabled(true);
                view.option2.setEnabled(true);
                view.option3.setEnabled(true);
                view.option4.setEnabled(true);
            }
        });

        view.buildButton.addActionListener(new ActionListener() {
            /**
             * Handles the event fired by the user on the GUI, when the Build Survey button is pressed, a new Survey
             * object will be instantiated and sent to database
             *
             * @param actionEvent actionEvent is an ActionEvent object that the user creates when they press the button
             *                    on the GUI
             */
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

    /**
     * Class that adds Item listener to the radio button groups on GUI
     */
    class MyRadioButtonItemListener implements ItemListener {
        /**
         * Checks when there is a state change for the radio buttons on the GUI,
         * switches state between SELECTED and DESELECTED when a radio button is pressed
         *
         * @param e e is an ItemEvent object that gets its source when a radio button is selected from the radio
         *          button group on our GUI
         */
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
