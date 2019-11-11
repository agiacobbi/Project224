import javax.swing.*;
import java.awt.*;

public class TitleScreenView extends JFrame {
    private JButton buildSurvey;
    private JButton takeSurvey;
    private JLabel title;

    public TitleScreenView() {
        super("Poll-A-Bear");

        setVisible(true);
        setPreferredSize(new Dimension(700,850));
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setupUI();
        pack();
    }

    private void setupUI() {
        JPanel panel = (JPanel) getContentPane();
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(0,2));

        buildSurvey = new JButton("Build Survey");
        buildSurvey.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        buildSurvey.setForeground(Color.magenta);
        takeSurvey = new JButton("Take Survey");
        takeSurvey.setFont(new Font("Times New Roman", Font.PLAIN, 40));
        takeSurvey.setForeground(Color.MAGENTA);

        bottomPanel.add(buildSurvey);
        bottomPanel.add(takeSurvey);

        title = new JLabel("Poll-A-Bear");
        title.setFont(new Font("Bernard MT Condensed", Font.PLAIN, 80));
        title.setForeground(Color.PINK);

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(title);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(bottomPanel, BorderLayout.SOUTH);
    }

}
