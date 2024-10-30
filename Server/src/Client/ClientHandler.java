package Client;

import Channel.ChannelManager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.List;

import static Send.Send.sendPersonal;

public class ClientHandler implements Runnable {
    Client client;
    Socket socket;
    List<Client> clientList = ClientList.getInstance();
    ChannelManager channelManager;

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

            // Receive userName from client
            String clientName = in.readUTF();

            // Create client
            client.setName(clientName);
            client.setSocket(socket);
            client.setStatus("Lobby");

            // Add client info in client list
            clientList.add(client);

            // Server log
            System.out.println("[Enter]: " + clientName);
            System.out.println("[Current]: " + clientList.size());

            // For test client
            sendPersonal(out, "[Server] Welcome" + clientName + "!");

            // Send channel list to client

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
