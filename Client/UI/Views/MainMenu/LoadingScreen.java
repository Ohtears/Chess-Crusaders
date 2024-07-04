package Client.UI.Views.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.sound.sampled.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LoadingScreen extends JFrame {

    public LoadingScreen() {
        setTitle("Loading Screen");
        setSize(960, 540);
        setLayout(null); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBounds(0, 0, 960, 540);
        ImageIcon backgroundImage = new ImageIcon(new ImageIcon("Client\\Assets\\Images\\Mis\\loading_screen_wallpaper.jpg")
                .getImage().getScaledInstance(960, 540, Image.SCALE_SMOOTH));
        backgroundLabel.setIcon(backgroundImage);
        add(backgroundLabel);

        JProgressBar progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(230, 460, 500, 30);
        progressBar.setStringPainted(true);
        backgroundLabel.add(progressBar);

        playMusic("Client\\Assets\\Audios\\GameSoundtrack\\mainmenumusic.wav");

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(new Runnable() {
            int progress = 0;

            @Override
            public void run() {
                if (progress <= 100) {
                    progressBar.setValue(progress);
                    progress++;
                } else {
                    executor.shutdown();
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new MainFrame();
                            dispose();
                        }
                    });
                }
            }
        }, 0, 30, TimeUnit.MILLISECONDS);

        setVisible(true);
    }

    private void playMusic(String musicFilePath) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File musicPath = new File(musicFilePath);
                    if (musicPath.exists()) {
                        AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioInput);
                        clip.start();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new LoadingScreen();
    }
}

