import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class TabbedPane extends JPanel {

    public TabbedPane() {
        super(new GridLayout(1, 1));
        setBackground(Color.CYAN);

        JTabbedPane tabbedPane = new JTabbedPane();

        JButton makeSurveyButton = new JButton("Make");
        JComponent panel1 = makePanel("Select a survey to add", makeSurveyButton);
        panel1.setBackground(Color.CYAN);
        tabbedPane.addTab("Make Survey", panel1);
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JButton selectSurveyButton = new JButton("Select");
        JComponent panel2 = makePanel("Select a survey to take", selectSurveyButton);
        tabbedPane.addTab("Take Survey", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        JButton button = new JButton("View");
        JComponent panel3 = makePanel("Select a survey to view results", button);
        tabbedPane.addTab("View Results", panel3);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        add(tabbedPane);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makePanel(String text, JButton button) {
        JPanel panel = new JPanel(false);
        panel.setBackground(Color.CYAN);

        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Panel"));


        JLabel message = new JLabel(text);
        message.setFont(new Font("Arial", Font.BOLD, 24));

        //TODO make this be centered on top
        panel.add(message, BorderLayout.NORTH);

        //TODO this is where we add the surveys from the data base to the scroll pane
        JScrollPane scrollPane = new JScrollPane();
        panel.add(scrollPane, BorderLayout.CENTER);

        panel.add(button, BorderLayout.SOUTH);

        return panel;
    }

    private static void createMainFrame() {
        JFrame mainFrame = new JFrame("Poll A Bear");

        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setPreferredSize(new Dimension(700, 850));

        mainFrame.add(new TabbedPane(), BorderLayout.CENTER);
        mainFrame.pack();
    }

    public static void main(String[] args) {
        createMainFrame();
    }
}
