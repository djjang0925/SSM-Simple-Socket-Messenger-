package Message;

import Channel.ChannelList;
import Client.ClientList;
import Client.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Send {
    public static void sendPersonal(Client client, String message) throws IOException {
        DataOutputStream out = client.getOut();
        out.writeUTF(message);
    }

    public static void sendChannel(int index, String message) throws IOException {
        List<String> channelClientList = ChannelList.getInstance().get(index).getChannelClientList();
        Map<String, Client> clientList = ClientList.getInstance();

        for (String name : channelClientList) {
            clientList.get(name).getOut().writeUTF(message);
        }
    }
}