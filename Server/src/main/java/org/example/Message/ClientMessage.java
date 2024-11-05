package org.example.Message;

public class ClientMessage {
    private String type;
    private String from;
    private int channelIndex;
    private String to;
    private String content;

    public String getType() {
        return type;
    }

    public String getFrom() {
        return from;
    }

    public int getChannelIndex() {
        return channelIndex;
    }

    public String getTo() {
        return to;
    }

    public String getContent() {
        return content;
    }
}
