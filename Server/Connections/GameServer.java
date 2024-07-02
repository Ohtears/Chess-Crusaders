package Server.Connections;

import java.io.*;
import java.net.*;
import java.util.*;

public class GameServer {
    private static final int PORT = 12345;
    private static final int BROADCAST_PORT = 9876;
    private static final String BROADCAST_MESSAGE = "GameServer:";

    private static List<ClientHandler> clients = new ArrayList<>();
    private static String serverName;

    public static void main(String[] args) {
        serverName = args.length > 0 ? args[0] : "Default Server";
        System.out.println("Starting server: " + serverName);

        new Thread(GameServer::broadcastServer).start();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void broadcastServer() {
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] buffer = (BROADCAST_MESSAGE + serverName).getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("255.255.255.255"), BROADCAST_PORT);
            while (true) {
                socket.send(packet);
                System.out.println("Broadcasting server presence: " + serverName);
                Thread.sleep(5000); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Received: " + message);
                    // Handle messages from clients
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
    }
}
