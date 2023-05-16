package exercise;
import exercise.connections.Connected;
import exercise.connections.Connection;
import exercise.connections.Disconnected;

import java.util.List;
import java.util.ArrayList;

// BEGIN
public class TcpConnection {
    String ip;
    int port;
    private Connection state;
    List<String> buffer = new ArrayList<>();

    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.state = new Disconnected(this);
    }

    public void connect() {
        state.connect();
    }

    public void write(String data) {
        state.write(data);
    }

    public void addToBuffer(String data) {
        buffer.add(data);
    }

    public void disconnect() {
        state.disconnect();
    }

    public void setState(Connection stateObject) {
        state = stateObject;
    }

    public String getCurrentState() {
        return state.getName();
    }
}
// END
