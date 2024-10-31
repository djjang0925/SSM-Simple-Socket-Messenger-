package Client;

import java.io.DataOutputStream;

/* Duplicate client names are not allowed. */
public class Client {
    String name;
    DataOutputStream out;
    String current;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }
}
