package Client.Connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import org.json.JSONObject;

import Client.Models.User;

public class GameClient {
    private String serverAddress;
    private int port;
    private User player;

    public GameClient(String serverAddress, int port, User player) {
        this.serverAddress = serverAddress;
        this.port = port;
        this.player = player;
    }

    public String connectToServer(RequestType requestType) {
        int attempts = 5; // Number of connection attempts
        while (attempts > 0) {
            System.out.println("Attempting to connect to server at " + serverAddress + ":" + port);
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
                        stringRequest = waitGame();
                        break;
                    default:
                        break;
                }
    
                System.out.println("Sending request: " + stringRequest);
                out.println(stringRequest);
                String response = in.readLine();
                System.out.println("Server response: " + response);
    
                return response;
            } catch (Exception e) {
                System.err.println("Error connecting to server: " + e.getMessage());
                e.printStackTrace();
                attempts--;
                try {
                    Thread.sleep(2000); // Wait before retrying
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
        return null;
    }
    

    private String createLobby(){
            
        JSONObject jsonRequest = JsonConvertor.createLobby(port, player);
        String stringRequest = jsonRequest.toString();
        
        return stringRequest;

    }

    private String joinLobby(){

        JSONObject jsonRequest = JsonConvertor.joinLobby(port, player);
        String stringRequest = jsonRequest.toString();
        
        return stringRequest;
    }

    private String startGame(){

        JSONObject jsonRequest = JsonConvertor.startGame(port, player);
        String stringRequest = jsonRequest.toString();
        
        return stringRequest;
    }
    private String waitGame(){

        JSONObject jsonRequest = JsonConvertor.waitForRequest();
        String stringRequest = jsonRequest.toString();

        return stringRequest;

    }
}
