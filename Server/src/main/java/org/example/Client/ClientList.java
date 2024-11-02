package org.example.Client;

import java.util.HashMap;
import java.util.Map;

/* For display client list on lobby and whisper */
public class ClientList {
    private static Map<String, Client> clientList;

    public static synchronized Map<String, Client> getInstance() {
        if (clientList == null) {
            clientList = new HashMap<>();
        }

        return clientList;
    }
}
