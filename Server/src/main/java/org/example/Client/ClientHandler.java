package org.example.Client;

import com.google.gson.Gson;
import org.example.Channel.Channel;
import org.example.Channel.ChannelToJson;
import org.example.Channel.ChannelList;
import org.example.Message.ClientMessage;
import org.example.Message.MessageHandler;
import org.example.Message.Send;
import org.example.Message.ServerMessage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

public class ClientHandler {
    Gson gson = new Gson();
    Client client;
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    MessageHandler messageHandler;
    Map<String, Client> clientList = ClientList.getInstance();

    public ClientHandler(Client client, Socket socket) {
        this.socket = socket;
        this.client = client;

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
                // Process message
                ClientMessage message = gson.fromJson(in.readUTF(), ClientMessage.class);
                messageHandler.Process(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnectClient();
        }
    }

    // Process
    private void initializeClient() throws IOException {
        // For test client
        out.writeUTF("[Server] 닉네임을 입력하세요: ");

        // Receive userName from client
        ClientMessage clientMessage = gson.fromJson(in.readUTF(), ClientMessage.class);

        if (Objects.equals(clientMessage.getType(), "initial")) {
            String userName = clientMessage.getFrom();

            /*
            Duplicate check code
            */

            // Set client data
            client.setName(userName);
            client.setOut(out);
            client.setCurrent(0);
            clientList.put(userName, client);

            // Add client in lobby
            ChannelList.getInstance().get(0).getChannelClientList().add(userName);

            // Server log
            System.out.println("[Enter]: " + userName);
            System.out.println("[Current]: " + clientList.size());

            // Send client list to lobby clients
            Send.sendChannel(0, ChannelToJson.getChannelClientList(0));

            // Send channel list to client
            Send.sendPrivate(userName, ChannelToJson.getChannelList());
        }
    }

    // need update(like leave channel... ect)
    private void disconnectClient() {
        try {
            String userName = client.getName();
            int channelIndex = client.getCurrent();

            LinkedList<String> channelClientList = ChannelList.getInstance().get(channelIndex).getChannelClientList();

            if (channelIndex == 0) {
                channelClientList.remove(userName);
            } else {
                if (channelClientList.size() == 1) {
                    ChannelList.getInstance().remove(channelIndex);
                } else {
                    channelClientList.remove(userName);

                    ServerMessage leaveMessage = new ServerMessage("message");
                    leaveMessage.setMessage(userName + " has leaved the chat.");

                    Send.sendChannel(channelIndex, gson.toJson(leaveMessage));
                }
            }

            Send.sendChannel(channelIndex, ChannelToJson.getChannelClientList(channelIndex));

            clientList.remove(userName);

            socket.close();
            System.out.println("[Leave]: " + userName);
            System.out.println("[Current]: " + clientList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
