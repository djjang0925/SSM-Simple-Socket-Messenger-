package Client;

import Channel.Channel;
import Channel.ChannelList;
import Channel.ChannelManager;
import Message.Send;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Map;

public class ClientHandler {
    Client client;
    Socket socket;
    ChannelManager channelManager;
    Channel lobby = ChannelList.getInstance().get(0);
    Map<String, Client> clientList = ClientList.getInstance();
    DataInputStream in;
    DataOutputStream out;

    public ClientHandler(Client client, Socket socket, ChannelManager channelManager) {
        this.socket = socket;
        this.client = client;
        this.channelManager = channelManager;

        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiveMessages() {
        try {
            // For test client
            out.writeUTF("[Server] 닉네임을 입력하세요: ");

            // Receive userName from client and duplicate check
            String clientName = in.readUTF();
            /*
            Duplicate check code
            */

            // Set client data and update lists
            client.setName(clientName);
            client.setOut(out);
            client.setStatus("Lobby");
            clientList.put(clientName, client);
            lobby.getChannelClientList().add(clientName);

            // Server log
            System.out.println("[Enter]: " + clientName);
            System.out.println("[Current]: " + clientList.size());

            // For test client
            Send.sendPersonal(client, "[Server] Welcome" + clientName + "!");

            // For test client
            Send.sendPersonal(client, "[Server] Client list");

            // Send client list to lobby clients
            Send.sendChannel(0, channelManager.getChannelClientList());

            // For test client
            Send.sendPersonal(client, "[Server] Channel list");

            // Send channel list to client
            Send.sendPersonal(client, channelManager.getChannelList());

            // Process additional incoming messages from client
            while (true) {
                String message = in.readUTF();
                // Handle message (e.g., joining channels, sending whispers, etc.)
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnectClient();
        }
    }

    public void sendMessages() {
        try {
            // Continuously send messages or updates to the client as needed
            // For example, it could listen for new events to notify the client
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // need update(like leave channel... ect)
    private void disconnectClient() {
        try {
            String clientName = client.getName();
            clientList.remove(clientName);
            lobby.getChannelClientList().remove(clientName);
            socket.close();
            System.out.println("[Leave]: " + clientName);
            System.out.println("[Current]: " + clientList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
