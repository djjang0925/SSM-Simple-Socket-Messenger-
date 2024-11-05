package org.example.Message;

import org.example.Channel.ChannelList;
import org.example.Client.Client;
import org.example.Client.ClientList;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Send {
    public static void sendPrivate(String userName, String message) throws IOException {
        DataOutputStream out = ClientList.getInstance().get(userName).getOut();
        out.writeUTF(message);
    }

    public static void sendChannel(int index, String message) throws IOException {
        List<String> channelClientList = ChannelList.getInstance().get(index).getChannelClientList();
        Map<String, Client> clientList = ClientList.getInstance();

        for (String name : channelClientList) {
            clientList.get(name).getOut().writeUTF(message);
        }
    }
}