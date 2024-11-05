package org.example.Message;

import com.google.gson.Gson;
import org.example.Channel.ChannelList;
import org.example.Channel.ChannelToJson;
import org.example.Client.Client;
import org.example.Client.ClientList;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class MessageHandler {
    Gson gson = new Gson();

    public void Process(ClientMessage message) throws IOException {
        String type = message.getType();
        String userName = message.getFrom();
        Client client = ClientList.getInstance().get(userName);
        LinkedList<String> lobbyClientList;
        LinkedList<String> channelClientList;
        int channelIndex;

        switch (type) {
            case "enter":
                channelIndex = message.getChannelIndex();

                /*
                1. remove userName from lobby
                2. add userName to channel
                3. set client status chanelIndex
                4. send lobbyClientList to lobby clients
                5. send channelClientList to channel clients
                6. send notification to channel clients
                */

                lobbyClientList = ChannelList.getInstance().get(0).getChannelClientList();
                channelClientList = ChannelList.getInstance().get(channelIndex).getChannelClientList();

                lobbyClientList.remove(userName);
                channelClientList.add(userName);
                client.setCurrent(channelIndex);

                Send.sendChannel(0, ChannelToJson.getChannelClientList(0));
                Send.sendChannel(channelIndex, ChannelToJson.getChannelClientList(channelIndex));

                ServerMessage joinMessage = new ServerMessage("message");
                joinMessage.setMessage(userName + " has entered the chat.");

                Send.sendChannel(channelIndex, gson.toJson(joinMessage));

                break;

            case "leave":
                channelIndex = client.getCurrent();

                /*
                1. remove userName from channel
                2. add userName to lobby
                3. send lobbyClientList to lobby clients
                4-1 if channel client list is empty
                    remove channel from channel list
                4-2 else
                    send channelClientList to channel clients
                    send notification to channel clients
                5. set client status lobby
                */

                lobbyClientList = ChannelList.getInstance().get(0).getChannelClientList();
                channelClientList = ChannelList.getInstance().get(channelIndex).getChannelClientList();

                channelClientList.remove(userName);
                lobbyClientList.add(userName);

                Send.sendChannel(0, ChannelToJson.getChannelClientList(0));

                if (channelClientList.isEmpty()) {
                    ChannelList.getInstance().remove(channelIndex);
                } else {
                    ServerMessage leaveMessage = new ServerMessage("message");
                    leaveMessage.setMessage(userName + " has leaved the chat.");

                    Send.sendChannel(channelIndex, ChannelToJson.getChannelClientList(channelIndex));
                    Send.sendChannel(channelIndex, gson.toJson(leaveMessage));
                }

                client.setCurrent(0);

                break;
                
            case "message":
                String to = message.getTo();

                if (Objects.equals(to, "channel")) {
                    Send.sendChannel(client.getCurrent(), gson.toJson(message));
                } else {
                    Send.sendPrivate(message.getTo(), gson.toJson(message));
                }
                break;
            default:
                break;
        }

    }
}
