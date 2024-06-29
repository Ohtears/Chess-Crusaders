package Client.UI.Views.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

public class MainMenu extends JFrame {

    private EmbeddedMediaPlayerComponent mediaPlayerComponent;

    public MainMenu() {
        // Set the title of the frame
        setTitle("Main Menu");

        // Set the size of the frame
        setSize(960, 540);

        // Set the layout of the frame
        setLayout(null);

        // Center the frame on the screen
        setLocationRelativeTo(null);

        // Exit the application when the frame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the media player component
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayerComponent.setBounds(0, 0, 960, 540);
        add(mediaPlayerComponent);

        // Play the video in a separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                mediaPlayerComponent.getMediaPlayer().playMedia("path/to/your/video.mp4");
                mediaPlayerComponent.getMediaPlayer().setRepeat(true);
            }
        }).start();

        // Create the buttons
        JButton singleplayerButton = createMenuButton("Singleplayer");
        JButton multiplayerButton = createMenuButton("Multiplayer");
        JButton settingsButton = createMenuButton("Settings");
        JButton quitButton = createMenuButton("Quit");

        // Position the buttons
        singleplayerButton.setBounds(30, 100, 150, 40);
        multiplayerButton.setBounds(30, 160, 150, 40);
        settingsButton.setBounds(30, 220, 150, 40);
        quitButton.setBounds(30, 280, 150, 40);

        // Add action listeners to the buttons
        singleplayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Singleplayer selected");
                // Add your singleplayer handling code here
            }
        });

        multiplayerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Multiplayer selected");
                // Add your multiplayer handling code here
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Settings selected");
                // Add your settings handling code here
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Quit selected");
                System.exit(0);
            }
        });

        // Add the buttons to the frame
        add(singleplayerButton);
        add(multiplayerButton);
        add(settingsButton);
        add(quitButton);

        // Make the frame visible
        setVisible(true);
    }

    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        new MainMenu();
    }
}
