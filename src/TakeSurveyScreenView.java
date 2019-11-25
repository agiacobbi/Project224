import javax.swing.*;
import java.awt.*;

public class TakeSurveyScreenView extends JFrame{
    private Controller controller;
    private JPanel addedQuestionsPanel;
    private int size;


    public TakeSurveyScreenView(Controller controller) {
        super("Take Survey");
        this.controller = controller;

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 850));
        setupUI();
        pack();
    }

    private void setupUI() {
        addedQuestionsPanel = new JPanel();
        addedQuestionsPanel.setLayout(new GridLayout(10, 1));
        addedQuestionsPanel.setBackground(Color.CYAN);

    }

    private void makeButtonGroup() {
        JRadioButton trueButton = new JRadioButton();
        JRadioButton falseButton = new JRadioButton();

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(trueButton);
        buttonGroup.add(falseButton);

    }

    public static void main(String[] args) {
        Survey model = new Survey("Survey");
        Controller controller = new Controller(model);
    }
}
