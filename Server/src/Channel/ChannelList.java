package Channel;

import java.util.HashMap;
import java.util.Map;

/* For display channel list on lobby */
/* Index 0 if for lobby */
public class ChannelList {
    private static Map<Integer, Channel> channelList;

    public static synchronized Map<Integer, Channel> getInstance() {
        if (channelList == null) {
            channelList = new HashMap<>();
            Channel lobby = new Channel();
            lobby.setName("lobby");

            channelList.put(0, lobby);
        }

        return channelList;
    }
}
