package org.example.Channel;

import java.util.LinkedList;

/* Duplicate channel names are allowed. */
/* Managed by channel index number. */
public class Channel {
    String name;
    // Design considering the future implementation of hosting features.
    // 0 index for host.
    // Can access by client list index.
    private final LinkedList<String> channelClientList = new LinkedList<>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized LinkedList<String> getChannelClientList() {
        return channelClientList;
    }
}
