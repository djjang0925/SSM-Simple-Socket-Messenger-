package Message;

import Channel.ChannelList;
import Client.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

public class Send {
    public static void sendPersonal(Client client, String message) throws IOException {
        DataOutputStream out = client.getOut();
        out.writeUTF(message);
    }

    public static void sendChannel(int index, String message) throws IOException {
        Map<String, Client> channelClientList = ChannelList.getInstance().get(index).getChannelClientList();

        for (Client client : channelClientList.values()) {
            client.getOut().writeUTF(message);
        }
    }
}