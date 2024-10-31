package Client;

import Channel.Channel;
import Channel.ChannelList;
import Channel.ChannelManager;
import Message.Send;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Map;

public class ClientHandler implements Runnable {
    Client client;
    Socket socket;
    ChannelManager channelManager;
    Channel lobby = ChannelList.getInstance().get(0);
    Map<String, Client> clientList = ClientList.getInstance();

    public ClientHandler(Client client, Socket socket, ChannelManager channelManager) {
        this.socket = socket;
        this.client = client;
        this.channelManager = channelManager;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // For test client
            out.writeUTF("[Server] 닉네임을 입력하세요: ");

            // Receive userName from client and duplicate check
            String clientName = in.readUTF();
            /*
            Duplicate check code
            */

            // Create client
            client.setOut(out);
            client.setStatus("Lobby");

            // Add client info in client list
            clientList.put(clientName, client);

            // Add client in lobby
            lobby.getChannelClientList().put(clientName, client);

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

            // Process incoming message
            // ex. Join channel, send whisper... ect

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
