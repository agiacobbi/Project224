import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TabbedPaneView extends JFrame {
    JTabbedPane tabbedPane;
    JButton createSurveyButton;
    JButton editSurveyButton;
    JButton takeSurveyButton;
    JButton viewResultsButton;
    JList<String> surveysList;
    JList<String> availableResultsList;
    JList<String> createSurveyList;

    public TabbedPaneView() {
        super("Poll A Bear");

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(700, 550));

        setupUI();
        pack();
    }

    private void setupUI() {
        JPanel mainPanel = (JPanel) getContentPane();
        setBackground(Color.CYAN);

        availableResultsList = new JList<>();
        availableResultsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        surveysList = new JList<>();
        surveysList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        createSurveyList = new JList<>();
        createSurveyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        setupTabbedPane();

        mainPanel.add(tabbedPane);
    }

    private void setupTabbedPane() {
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Make Survey", makeSurveyPanel());
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        tabbedPane.addTab("Take Survey", takeSurveyPanel());
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        tabbedPane.addTab("View Results", viewSurveyPanel());
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    private Component viewSurveyPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);

        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel message = new JLabel("Select a survey to view its results");
        message.setFont(new Font("Arial", Font.BOLD, 24));

        //TODO make this be centered on top
        panel.add(message, BorderLayout.NORTH);

        //TODO this is where we add the surveys from the data base to the scroll pane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.CYAN);

        scrollPane.setViewportView(availableResultsList);

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.CYAN);
        viewResultsButton = new JButton("View Results");
        buttonPanel.add(viewResultsButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    private Component takeSurveyPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);

        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel message = new JLabel("Select a survey to take");
        message.setFont(new Font("Arial", Font.BOLD, 24));

        //TODO make this be centered on top
        panel.add(message, BorderLayout.NORTH);

        //TODO this is where we add the surveys from the data base to the scroll pane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.CYAN);

        scrollPane.setViewportView(surveysList);

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.CYAN);
        takeSurveyButton = new JButton("Take Survey");
        buttonPanel.add(takeSurveyButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    protected JComponent makeSurveyPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.CYAN);

        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel message = new JLabel("Select a survey to edit, or click \"Create Survey\" to build a new one");
        message.setFont(new Font("Arial", Font.BOLD, 24));

        //TODO make this be centered on top
        panel.add(message, BorderLayout.NORTH);

        //TODO this is where we add the surveys from the data base to the scroll pane
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.CYAN);

        scrollPane.setViewportView(createSurveyList);

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.CYAN);
        createSurveyButton = new JButton("New Survey");
        buttonPanel.add(createSurveyButton);
        editSurveyButton = new JButton("Edit Survey");
        buttonPanel.add(editSurveyButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

}