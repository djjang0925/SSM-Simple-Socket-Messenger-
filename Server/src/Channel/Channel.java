package Channel;

import Client.Client;

import java.util.HashMap;
import java.util.Map;


/* Duplicate channel names are allowed. */
/* Managed by channel index number. */
public class Channel {
    String name;
    // Design considering the future implementation of hosting features.
    // 0 index for host.
    // Can access by client list index.
    Map<String, Client> channelClientList = new HashMap<>();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Client> getChannelClientList() {
        return this.channelClientList;
    }
}
