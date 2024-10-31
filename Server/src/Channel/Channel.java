package Channel;

import Client.Client;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/* Duplicate channel names are allowed. */
/* Managed by channel index number. */
public class Channel {
    String name;
    // Design considering the future implementation of hosting features.
    // 0 index for host.
    // Can access by client list index.
    private final List<String> channelClientList = new LinkedList<>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public synchronized List<String> getChannelClientList() {
        return channelClientList;
    }
}
