package Client.UI.Views.MainMenu;

import Client.Connections.ClientDiscovery;
import Client.Connections.GameClient;
import Client.Connections.RequestType;
import Client.Models.User;
import Client.UI.Views.Lobby;
import Client.UI.Views.PanelSwitcher;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Multiplayer extends JPanel implements PanelSwitcher {
    @SuppressWarnings("unused")
    private List<String> availableServers = new ArrayList<>();
    private DefaultListModel<String> serverListModel = new DefaultListModel<>();
    private JList<String> serverList;
    private ClientDiscovery discoveryThread;

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
        JButton backButton = new JButton("Back to Main Menu"); 


        addServerButton.setBounds(200, 370, 150, 40);
        createServerButton.setBounds(380, 370, 150, 40);
        joinServerButton.setBounds(560, 370, 150, 40);
        backButton.setBounds(30, 370, 150, 40); 


        joinServerButton.addActionListener(e -> {
            List <User> players = new ArrayList<>();

            String selectedServer = serverList.getSelectedValue();
            if (selectedServer != null) {
                System.out.println("Joining server: " + selectedServer);
                new Thread(() -> {
                    String[] parts = selectedServer.split(" - /");
                    String serverAddress = parts[2].trim();
                    int port = Integer.parseInt(parts[1].trim());
                    System.out.println(serverAddress + ":" + port);

                    String playerName = JOptionPane.showInputDialog(this, "Enter your name:", "Player Name Input", JOptionPane.PLAIN_MESSAGE);
                    User player2 = new User(2, playerName, "Muslim");

                    GameClient client = new GameClient(serverAddress, port, player2);
                    String opponentString = client.connectToServer(RequestType.JOINLOBBY);
                    User player1 = new User(1, opponentString, "Crusader");
                    players.add(player1);
                    
                    switchPanel(new Lobby(players), "Lobby");

                }).start();
            } else {
                System.out.println("No server selected");
            }
        });

        createServerButton.addActionListener(e -> {
            List <User> players = new ArrayList<>();

            String serverName = JOptionPane.showInputDialog(this, "Enter server name:");
            if (serverName != null && !serverName.trim().isEmpty()) {
                new Thread(() -> {
                    int port = requestServerCreation(serverName);
                    if (port != -1) {
                        
                        String playerName = JOptionPane.showInputDialog(this, "Enter your name:", "Player Name Input", JOptionPane.PLAIN_MESSAGE);
                        User player1 = new User(1, playerName, "Crusader");
                        players.add(player1);
                        GameClient client = new GameClient("192.168.1.5", port, player1);
                        String opponent_username = client.connectToServer(RequestType.CREATELOBBY);
                        User player2 = new User(2, opponent_username, "Muslim");
                        players.add(player2);
                        switchPanel(new Lobby(players), "Lobby");

                    }
                }).start();
                switchPanel(new Lobby(players), "Lobby");
            }
        });

        backButton.addActionListener(e -> {

            switchPanel(new MainMenu(), "MainMenu");
        });

        background.setLayout(null);
        background.add(scrollPane);
        background.add(addServerButton);
        background.add(createServerButton);
        background.add(joinServerButton);
        background.add(backButton);

        discoveryThread = new ClientDiscovery(serverListModel);
        createDiscovery();

    }
    private void createDiscovery() {
        new Thread(() -> {
            discoveryThread.discoverServers();
        }).start();
    }


    private int requestServerCreation(String serverName) {
        String managementServerAddress = "192.168.1.5";
        int managementServerPort = 12346;

        try (Socket socket = new Socket(managementServerAddress, managementServerPort);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println(serverName);
            String response = in.readLine();
            System.out.println("Server creation response: " + response);
            if (response.startsWith("Server created on port:")) {
                return Integer.parseInt(response.split(": ")[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1; 
    }

    @Override
    public void switchPanel(JPanel panel, String Constraint) {
        discoveryThread.stopDiscovery();

        MainFrame.mainPanel.removeAll();
        MainFrame.mainPanel.add(panel, Constraint);
        MainFrame.mainPanel.revalidate();
        MainFrame.mainPanel.repaint();
    }

    
}
