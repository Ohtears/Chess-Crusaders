package Client.UI.Views.MainMenu;

import java.awt.CardLayout;

import javax.swing.*;

public class MainFrame extends JFrame {

    public static JPanel mainPanel;

    public MainFrame() {
        setTitle("Main Menu");
        setSize(960, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        MainMenu menuPanel = new MainMenu();

        mainPanel.add(menuPanel, "Main Menu");

        add(mainPanel);

        setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}
