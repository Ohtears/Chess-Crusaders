package Client.UI.Views.MainMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;

import Client.Controllers.GameBoard;
import Client.UI.Views.Game.GameBoardUI;
import Client.Models.LayoutGame;

public class Multiplayer extends JPanel {
    private DefaultListModel<String> serverListModel = new DefaultListModel<>();
    private JList<String> serverList = new JList<>(serverListModel);
    private JButton joinServerButton = new JButton("Join Server");
    private JButton createServerButton = new JButton("Create Server");

    public Multiplayer() {

        ImageIcon imageIcon = new ImageIcon("Client/Assets/Images/Mis/loading_screen_wallpaper.jpg");
        Image image = imageIcon.getImage().getScaledInstance(960, 540, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(image));
        background.setBounds(0, 0, 960, 540);
        add(background);

        serverList.setBounds(30, 100, 300, 300);
        joinServerButton.setBounds(350, 100, 150, 40);
        createServerButton.setBounds(350, 160, 150, 40);

        joinServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedServer = serverList.getSelectedValue();
                if (selectedServer != null) {
                    joinServer(selectedServer);
                }
            }
        });

        createServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String serverName = JOptionPane.showInputDialog("Enter server name:");
                if (serverName != null && !serverName.trim().isEmpty()) {
                    createServer(serverName);
                }
            }
        });

        background.setLayout(null);
        background.add(new JScrollPane(serverList));
        background.add(joinServerButton);
        background.add(createServerButton);

        setVisible(true);

        new Thread(this::discoverServers).start();
    }

    private void joinServer(String serverName) {
        new Thread(() -> {
            try (Socket socket = new Socket("localhost", 12345);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                // Here you can send/receive messages to/from the game server
                GameBoard gameBoard = new GameBoard();
                gameBoard.initializeBoard(LayoutGame.Layout.DEFAULT);
                new GameBoardUI(gameBoard);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void createServer(String serverName) {
        new Thread(() -> {
            String managementServerAddress = "localhost"; 
            int managementServerPort = 12346;

            try (Socket socket = new Socket(managementServerAddress, managementServerPort);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                out.println(serverName);
                String response = in.readLine();
                System.out.println("Server creation response: " + response);

                joinServer(serverName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private void discoverServers() {
        try (DatagramSocket socket = new DatagramSocket(9876)) {
            socket.setSoTimeout(5000);

            while (true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String message = new String(packet.getData(), 0, packet.getLength());
                if (message.startsWith("GameServer:")) {
                    String serverName = message.substring("GameServer:".length());
                    if (!serverListModel.contains(serverName)) {
                        serverListModel.addElement(serverName);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Multiplayer::new);
    }
}
