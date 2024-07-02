package Server.Connections;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class ServerManagementService {
    private static final int MANAGEMENT_PORT = 12346;
    private static final List<String> activeServers = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Server Management Service started...");

        try {
            InetAddress localAddress = InetAddress.getByName("localhost");
            @SuppressWarnings("resource")
            ServerSocket managementSocket = new ServerSocket(MANAGEMENT_PORT, 50, localAddress);

            while (true) {
                Socket clientSocket = managementSocket.accept();
                new Thread(new ServerCreationHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ServerCreationHandler implements Runnable {
        private Socket socket;

        public ServerCreationHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
                 
                String serverName = in.readLine();
                if (serverName != null) {
                    System.out.println("Received request to create server: " + serverName);
                    activeServers.add(serverName);
                    startNewServer(serverName);
                    out.println("Server created: " + serverName);
                } else {
                    out.println("Invalid server name");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void startNewServer(String serverName) {
            new Thread(() -> GameServer.main(new String[]{serverName})).start();
        }
    }

    public static List<String> getActiveServers() {
        return activeServers;
    }
}
