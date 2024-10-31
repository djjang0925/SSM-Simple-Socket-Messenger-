package Channel;

import Client.Client;

import java.util.List;
import java.util.Map;

public class ChannelManager {
    StringBuilder sb = new StringBuilder();

    // Channel list for lobby clients
    public String getChannelList() {
        sb.setLength(0);

        Map<Integer, Channel> channelList = ChannelList.getInstance();

        if (channelList.isEmpty()) {
            return "Sorry, there are no active channels in Messenger.";
        }

        for (int index : channelList.keySet()) {
            sb.append(index).append(". ").append(channelList.get(index).getName()).append('\n');
        }

        return sb.toString();
    }

    // Client list for channel clients
    public String getChannelClientList() {
        sb.setLength(0);

        List<String> channelClientList = ChannelList.getInstance().get(0).getChannelClientList();

        for (String name : channelClientList) {
            sb.append(name).append('\n');
        }

        return sb.toString();
    }
}
