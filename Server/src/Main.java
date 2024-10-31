import Client.Client;
import Client.ClientHandler;
import Channel.ChannelHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args)
    {
        Socket socket;
        ServerSocket serverSocket;
        ChannelHandler channelHandler = new ChannelHandler();

        // Generate Thread pool for receive and send message
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        try {
            serverSocket = new ServerSocket(8888);
            System.out.println("[Server]: Start");

            while(true) {
                socket = serverSocket.accept();
                Client client = new Client();
                ClientHandler clientHandler = new ClientHandler(client, socket, channelHandler);

                /* Submit incoming and outgoing tasks to the thread pool
                to ensure that client requests are processed asynchronously
                at the same time */
                threadPool.submit(() -> {
                    try {
                        clientHandler.handleClient();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // When shutdown the server, exit the thread pool to clean up resources
            threadPool.shutdown();
        }
    }
}