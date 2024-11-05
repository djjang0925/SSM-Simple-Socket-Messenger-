package org.example.Message;

import org.example.Channel.Channel;

import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ServerMessage {
    private String type;
    private String from = "Server";
    private String message;
    private LinkedList<String> clientList;
    private LinkedHashMap<Integer, Channel> channelList;

    public ServerMessage(String type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setClientList(LinkedList<String> clientList) {
        this.clientList = clientList;
    }

    public void setChannelList(LinkedHashMap<Integer, Channel> channelList) {
        this.channelList = channelList;
    }
}
