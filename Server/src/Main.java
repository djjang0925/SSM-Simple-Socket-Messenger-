import Client.Client;
import Client.ClientHandler;
import Channel.ChannelManager;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args)
    {
        Socket socket;
        ServerSocket serverSocket;
        Client client = new Client();
        ChannelManager channelManager = new ChannelManager();

        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("[Server]: Start");

            while(true) {
                socket = serverSocket.accept();
                new Thread(new ClientHandler(client, socket, channelManager)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}