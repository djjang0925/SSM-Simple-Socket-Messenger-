package Client;

import java.io.DataOutputStream;

/* Duplicate client names are not allowed. */
public class Client {
    DataOutputStream out;
    String status;

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
