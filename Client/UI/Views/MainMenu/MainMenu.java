package Client.UI.Views.MainMenu;

import javax.swing.*;

import Client.UI.Views.PanelSwitcher;

import java.awt.*;

public class MainMenu extends JFrame implements PanelSwitcher {

    private JPanel mainPanel;

    public MainMenu() {
        setTitle("Main Menu");
        setSize(960, 540);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        add(mainPanel);

        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, "Main Menu");

        setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon("Client\\Assets\\Images\\Mis\\loading_screen_wallpaper.jpg");
        Image image = imageIcon.getImage().getScaledInstance(960, 540, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(image));
        background.setBounds(0, 0, 960, 540);
        menuPanel.add(background);

        JButton singleplayerButton = new JButton("Singleplayer");
        JButton multiplayerButton = new JButton("Multiplayer");
        JButton settingsButton = new JButton("Settings");
        JButton quitButton = new JButton("Quit");

        singleplayerButton.setBounds(30, 100, 150, 40);
        multiplayerButton.setBounds(30, 160, 150, 40);
        settingsButton.setBounds(30, 220, 150, 40);
        quitButton.setBounds(30, 280, 150, 40);

        singleplayerButton.addActionListener(e -> System.out.println("Singleplayer selected"));
        multiplayerButton.addActionListener(e -> switchToMultiplayer());
        settingsButton.addActionListener(e -> System.out.println("Settings selected"));
        quitButton.addActionListener(e -> {
            System.out.println("Quit selected");
            System.exit(0);
        });

        background.setLayout(null); 
        background.add(singleplayerButton);
        background.add(multiplayerButton);
        background.add(settingsButton);
        background.add(quitButton);

        return menuPanel;
    }

    private void switchToMultiplayer() {
        Multiplayer multiplayerPanel = new Multiplayer();
        switchPanel(multiplayerPanel);
    }

    @Override
    public void switchPanel(JPanel panel) {
        mainPanel.add(panel, "Multiplayer");
        CardLayout layout = (CardLayout) mainPanel.getLayout();
        layout.show(mainPanel, "Multiplayer");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
