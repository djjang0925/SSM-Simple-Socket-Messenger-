package Client;

import Channel.Channel;
import Channel.ChannelList;
import Channel.ChannelHandler;
import Message.Send;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

public class ClientHandler {
    Client client;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    ChannelHandler channelHandler;
    Channel lobby = ChannelList.getInstance().get(0);
    Map<String, Client> clientList = ClientList.getInstance();

    public ClientHandler(Client client, Socket socket, ChannelHandler channelHandler) {
        this.socket = socket;
        this.client = client;
        this.channelHandler = channelHandler;

        try {
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleClient() {
        try {
            initializeClient();

            while (true) {
                String message = in.readUTF();

                // Process message
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnectClient();
        }
    }

    private void initializeClient() throws IOException {
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
        client.setCurrent("Lobby");
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
        Send.sendChannel(0, channelHandler.getChannelClientList());

        // For test client
        Send.sendPersonal(client, "[Server] Channel list");

        // Send channel list to client
        Send.sendPersonal(client, channelHandler.getChannelList());

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
