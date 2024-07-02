package Client.UI.Views.MainMenu;

import Client.Connections.ClientDiscovery;
import Client.Connections.GameClient;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Multiplayer extends JPanel {
    @SuppressWarnings("unused")
    private List<String> availableServers = new ArrayList<>();
    private DefaultListModel<String> serverListModel = new DefaultListModel<>();
    private JList<String> serverList;

    public Multiplayer() {

        ImageIcon imageIcon = new ImageIcon("Client\\Assets\\Images\\Mis\\loading_screen_wallpaper.jpg");
        Image image = imageIcon.getImage().getScaledInstance(960, 540, Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(image));
        background.setBounds(0, 0, 960, 540);
        add(background);

        serverList = new JList<>(serverListModel);
        serverList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        serverList.setBounds(200, 50, 560, 300);
        JScrollPane scrollPane = new JScrollPane(serverList);
        scrollPane.setBounds(200, 50, 560, 300);
        add(scrollPane);

        JButton addServerButton = new JButton("Add Server");
        JButton createServerButton = new JButton("Create Server");
        JButton joinServerButton = new JButton("Join Server");

        addServerButton.setBounds(200, 370, 150, 40);
        createServerButton.setBounds(380, 370, 150, 40);
        joinServerButton.setBounds(560, 370, 150, 40);

        joinServerButton.addActionListener(e -> {
            String selectedServer = serverList.getSelectedValue();
            if (selectedServer != null) {
                System.out.println("Joining server: " + selectedServer);
                new Thread(() -> {
                    String serverAddress = selectedServer.split(" - ")[1].trim();
                    GameClient client = new GameClient(serverAddress);
                    client.connectToServer();
                }).start();
            } else {
                System.out.println("No server selected");
            }
        });

        createServerButton.addActionListener(e -> {
            String serverName = JOptionPane.showInputDialog(this, "Enter server name:");
            if (serverName != null && !serverName.trim().isEmpty()) {
                new Thread(() -> requestServerCreation(serverName)).start();
            }
        });

        background.setLayout(null);
        background.add(scrollPane);
        background.add(addServerButton);
        background.add(createServerButton);
        background.add(joinServerButton);

        new Thread(() -> {
            ClientDiscovery discovery = new ClientDiscovery(serverListModel);
            discovery.discoverServers();
        }).start();
    }

    private void requestServerCreation(String serverName) {
        String managementServerAddress = "127.0.0.1"; 
        int managementServerPort = 12346;

        try (Socket socket = new Socket(managementServerAddress, managementServerPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(serverName);
            String response = in.readLine();
            System.out.println("Server creation response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
