package Client.UI.Views;

import javax.swing.*;

import Client.Models.User;

import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Lobby extends JPanel {

    private BufferedImage backgroundImg, playerListImg, boardImg, boardFrameImg, layoutListImg, modifiersImg, playerPanel;

    public Lobby() {

        this.setPreferredSize(new Dimension(960, 540));
        this.setLayout(null);

        try {
            backgroundImg = ImageIO.read(new File("Client\\Assets\\Images\\Mis\\loading_screen_wallpaper.jpg"));
            playerListImg = ImageIO.read(new File("Client\\Assets\\Images\\Mis\\playerlist.png"));
            boardImg = ImageIO.read(new File("Client\\Assets\\Images\\Mis\\board1.png"));
            boardFrameImg = ImageIO.read(new File("Client\\Assets\\Images\\Mis\\frameborder.png"));
            layoutListImg = ImageIO.read(new File("Client\\Assets\\Images\\Mis\\borderlayout.png"));
            modifiersImg = ImageIO.read(new File("Client\\Assets\\Images\\Mis\\modifier.png"));
            playerPanel = ImageIO.read(new File("Client\\Assets\\Images\\Mis\\player.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // JPanel colorPanel = createImagePanel(playerPanel, 0.97f, 312, 30);
        // colorPanel.setBounds(49, 219, 312, 30);
        // // JLabel colorPicker = new JLabel();
        // // colorPicker.setOpaque(true);
        // // colorPicker.setBackground(Color.GREEN); 
        // // colorPicker.setBounds(288, 222, 18, 18);
        // // colorPanel.add(colorPicker);
        // this.add(colorPanel);

        JPanel playerpnl = new JPanel();
        playerpnl.setBounds(49, 219, 312, 30);

        // JLabel player = new JLabel(Player.getUsername());
        // playerpnl.add(player);
        // this.add(playerpnl);

        JPanel playerListPanel = createImagePanel(playerListImg, 0.95f, 478, 341);
        playerListPanel.setBounds(29, 180, 478, 341);
        
        this.add(playerListPanel);


        JPanel boardPanel = createImagePanel(boardImg, 0.88f, 219, 218);
        boardPanel.setBounds(727, 9, 219, 218);
        this.add(boardPanel);

        JPanel boardFramePanel = createImagePanel(boardFrameImg, 1.0f, 227, 230);
        boardFramePanel.setBounds(723, 3, 227, 230);
        this.add(boardFramePanel);

        JPanel layoutListPanel = createImagePanel(layoutListImg, 0.95f, 265, 189);
        layoutListPanel.setBounds(681, 270, 265, 189);
        this.add(layoutListPanel);

        JButton startGameButton = new JButton("Start Game");
        startGameButton.setBounds(720, 470, 150, 30);
        startGameButton.addActionListener(e -> {


        });
        this.add(startGameButton);

        JPanel modifiersPanel = createImagePanel(modifiersImg, 0.95f, 201, 143);
        modifiersPanel.setBounds(29, 9, 201, 143);
        this.add(modifiersPanel);

    }

    private JPanel createImagePanel(BufferedImage image, float opacity, int width, int height) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
                g2d.drawImage(image, 0, 0, width, height, null);
                g2d.dispose();
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(width, height);
            }
        };
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImg, 0, 0, getWidth(), getHeight(), this);
    }

    // private void setPlayer(User user){

    //     players.add(user);

    // }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Lobby());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
