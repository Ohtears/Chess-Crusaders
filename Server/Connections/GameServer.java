package Server.Connections;

import java.io.*;
import java.net.*;
import java.util.*;

import Client.Controllers.GameBoard;
import Client.Models.LayoutGame;;

public class GameServer {
    private static final int GAME_PORT = 12345;
    private static final Map<Socket, PrintWriter> clientOutputs = new HashMap<>();
    private static String serverName;
    private static GameBoard gameBoard;

    public static void main(String[] args) {
        serverName = args[0];
        System.out.println("Game Server \"" + serverName + "\" started...");

        gameBoard = new GameBoard();
        gameBoard.initializeBoard(LayoutGame.Layout.DEFAULT);

        try (ServerSocket serverSocket = new ServerSocket(GAME_PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                clientOutputs.put(socket, out);

                sendInitialGameState(out);

                String message;
                while ((message = in.readLine()) != null) {
                    processClientMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clientOutputs.remove(socket);
            }
        }

        private void sendInitialGameState(PrintWriter out) {
            // Send the initial game state to the client
            // For example, serialize the gameBoard and send it
            // You need to implement the serialization and deserialization logic
            // out.println(serializeGameBoard(gameBoard));
        }

        private void processClientMessage(String message) {
            // Process messages from clients, update game state, and broadcast updates
            // For example, move a unit based on client input
            // Update game state and broadcast the new state
            // broadcastMessage(serializeGameBoard(gameBoard));
        }

        @SuppressWarnings("unused")
        private void broadcastMessage(String message) {
            for (PrintWriter writer : clientOutputs.values()) {
                writer.println(message);
            }
        }
    }
}
