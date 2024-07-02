package Client.Connections;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import javax.swing.DefaultListModel;

public class ClientDiscovery {
    private static final int BROADCAST_PORT = 9876;
    private DefaultListModel<String> serverListModel;

    public ClientDiscovery(DefaultListModel<String> serverListModel) {
        this.serverListModel = serverListModel;
    }

    public void discoverServers() {
        try (DatagramSocket socket = new DatagramSocket(BROADCAST_PORT)) {
            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            while (true) {
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                if (received.startsWith("GameServer:")) {
                    String serverInfo = received.substring("GameServer:".length());
                    String serverAddress = packet.getAddress().toString();
                    String serverEntry = serverInfo + " - " + serverAddress;
                    if (!serverListModel.contains(serverEntry)) {
                        serverListModel.addElement(serverEntry);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
