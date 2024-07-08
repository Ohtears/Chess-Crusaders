package Client.Connections;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import javax.swing.DefaultListModel;

public class ClientDiscovery {
    private static final int BROADCAST_PORT = 9876;
    private DefaultListModel<String> serverListModel;
    private volatile boolean running; 

    public ClientDiscovery(DefaultListModel<String> serverListModel) {
        this.serverListModel = serverListModel;
        this.running = true; 
    }

    public void discoverServers() {
        try {
            DatagramSocket socket = new DatagramSocket(null);
            socket.setReuseAddress(true);
            socket.bind(new InetSocketAddress(BROADCAST_PORT)); 
            socket.setBroadcast(true);
            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (running) {
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

    public void stopDiscovery() {
        running = false;
    }
}
