import Client.Client;
import Client.ClientHandler;
import Channel.ChannelManager;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args)
    {
        Socket socket;
        ServerSocket serverSocket;
        ChannelManager channelManager = new ChannelManager();

        // Generate Thread pool for receive and send message
        ExecutorService receivePool = Executors.newFixedThreadPool(10);
        ExecutorService sendPool = Executors.newFixedThreadPool(10);

        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("[Server]: Start");

            while(true) {
                socket = serverSocket.accept();

                Client client = new Client();
                ClientHandler clientHandler = new ClientHandler(client, socket, channelManager);

                /* Submit incoming and outgoing tasks to the thread pool
                to ensure that client requests are processed asynchronously
                at the same time */
                receivePool.submit(() -> {
                    try {
                        clientHandler.receiveMessages();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                sendPool.submit(() -> {
                    try {
                        clientHandler.sendMessages();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // When shutdown the server, exit the thread pool to clean up resources
            receivePool.shutdown();
            sendPool.shutdown();
        }
    }
}