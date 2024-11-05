package org.example.Channel;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/* For display channel list on lobby */
/* Index 0 if for lobby */
public class ChannelList {
    private static LinkedHashMap<Integer, Channel> channelList;

    public static synchronized LinkedHashMap<Integer, Channel> getInstance() {
        if (channelList == null) {
            channelList = new LinkedHashMap<>();
            Channel lobby = new Channel();
            lobby.setName("lobby");

            channelList.put(0, lobby);
        }

        return channelList;
    }
}
