package Client.Connections;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private String serverAddress;

    public GameClient(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public void connectToServer() {
        try (Socket socket = new Socket(serverAddress, 12345);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            out.println("Hello Server");
            String response = in.readLine();
            System.out.println("Server response: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
