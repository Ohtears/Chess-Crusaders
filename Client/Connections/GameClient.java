package Client.Connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONObject;

import Client.Models.User;

public class GameClient {
    private static final Logger logger = Logger.getLogger(GameClient.class.getName());

    private String serverAddress;
    private int port;
    private User player;

    public GameClient(String serverAddress, int port, User player) {
        this.serverAddress = serverAddress;
        this.port = port;
        this.player = player;
    }

    public String connectToServer(RequestType requestType) {
        try (Socket socket = new Socket(serverAddress, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            
            String stringRequest = null;

            switch (requestType) {
                case CREATELOBBY:
                    stringRequest = createLobby();
                    break;
                case JOINLOBBY:
                    stringRequest = joinLobby();
                    break;
                case STARTGAME:
                    stringRequest = startGame();
                    break;
                case WAITGAME:
                    // No request needed for WAITGAME
                    stringRequest = null;
                    break;
                default:
                    logger.log(Level.WARNING, "Unknown request type: {0}", requestType);
                    break;
            }

            if (stringRequest != null) {
                out.println(stringRequest);
                logger.log(Level.INFO, "Sent request to server: {0}", stringRequest);
            } else {
                logger.log(Level.INFO, "No request sent for type: {0}", requestType);
            }

            String response = in.readLine();
            logger.log(Level.INFO, "Received response from server: {0}", response);

            return response;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error communicating with server", e);
            e.printStackTrace();
        }
        return null;
    }

    private String createLobby() {
        JSONObject jsonRequest = JsonConvertor.createLobby(port, player);
        String stringRequest = jsonRequest.toString();
        return stringRequest;
    }

    private String joinLobby() {
        JSONObject jsonRequest = JsonConvertor.joinLobby(port, player);
        String stringRequest = jsonRequest.toString();
        return stringRequest;
    }

    private String startGame() {
        JSONObject jsonRequest = JsonConvertor.startGame(port, player);
        String stringRequest = jsonRequest.toString();
        return stringRequest;
    }
}
