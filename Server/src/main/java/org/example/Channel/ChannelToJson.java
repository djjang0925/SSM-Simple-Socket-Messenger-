package org.example.Channel;

import com.google.gson.Gson;
import org.example.Message.ServerMessage;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ChannelToJson {
    static Gson gson = new Gson();

    // Channel list for lobby clients
    public static String getChannelList() {
        ServerMessage message = new ServerMessage("channelList");
        LinkedHashMap<Integer, Channel> channelList = ChannelList.getInstance();
        message.setChannelList(channelList);

        return gson.toJson(message);
    }

    // Client list for channel clients
    public static String getChannelClientList(int channelIndex) {
        ServerMessage message = new ServerMessage("clientList");
        LinkedList<String> channelClientList = ChannelList.getInstance().get(channelIndex).getChannelClientList();
        message.setClientList(channelClientList);


        return gson.toJson(message);
    }
}
