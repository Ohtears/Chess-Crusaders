package Server.Connections;

import java.io.*;
import java.net.*;

public class ServerManagementService {
    private static final int MANAGEMENT_PORT = 12346;
    private static int nextPort = 12347;

    public static void main(String[] args) {
        System.out.println("Server Management Service started...");

        try (ServerSocket managementSocket = new ServerSocket(MANAGEMENT_PORT)) {
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
                    int port = getNextPort();
                    startNewServer(serverName, port);
                    out.println("Server created on port: " + port);
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

        private int getNextPort() {
            synchronized (ServerManagementService.class) {
                return nextPort++;
            }
        }

        private void startNewServer(String serverName, int port) {
            final int finalPort = port;
            new Thread(() -> GameServer.main(new String[]{serverName, String.valueOf(finalPort)})).start();
        }
    }
}
