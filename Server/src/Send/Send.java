package Send;

import java.io.DataOutputStream;
import java.io.IOException;

public class Send {
    public static void sendPersonal(DataOutputStream out, String message) throws IOException {
        out.writeUTF(message);
    }
}
