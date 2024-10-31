package Client;

import java.io.DataOutputStream;

/* Duplicate client names are not allowed. */
public class Client {
    String name;
    DataOutputStream out;
    String status;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
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
