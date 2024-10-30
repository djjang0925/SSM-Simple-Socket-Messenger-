package Client;

import java.util.ArrayList;
import java.util.List;

/* For display client list on lobby and whisper */
public class ClientList {
    private static List<Client> clientList;

    public static synchronized List<Client> getInstance() {
        if (clientList == null) {
            clientList = new ArrayList<>();
        }

        return clientList;
    }
}
