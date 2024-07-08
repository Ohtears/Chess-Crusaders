package Client.UI.Views.MainMenu;

import javax.swing.*;

import Client.UI.Views.PanelSwitcher;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainMenu extends JPanel implements PanelSwitcher {

    public MainMenu() {

        Path path_background = Paths.get("Client", "Assets", "Images", "Mis", "loading_screen_wallpaper.jpg");

        ImageIcon imageIcon = new ImageIcon(path_background.toString());
        Image image = imageIcon.getImage().getScaledInstance(960, 540, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(image));
        background.setBounds(0, 0, 960, 540);
        add(background);

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
    }

    private void switchToMultiplayer() {
        Multiplayer multiplayerPanel = new Multiplayer();
        switchPanel(multiplayerPanel, "Multiplayer");
    }
    @Override
    public void switchPanel(JPanel panel, String Constraint) {
        MainFrame.mainPanel.add(panel, Constraint);
        CardLayout layout = (CardLayout) MainFrame.mainPanel.getLayout();
        layout.show(MainFrame.mainPanel, Constraint);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
