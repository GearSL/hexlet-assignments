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
    public TcpConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void connect() {
        if(state == null){
            state = new Connected(this);
        } else {
            state.connect();
        }
    }

    public void write(String data) {
        state.write(data);
    }

    public void disconnect() {
        if(state.getCurrentState().equals("disconnected")) {
            state.disconnect();
        } else {
            state = new Disconnected(this);
        }
    }

    public String getCurrentState() {
        return state.getCurrentState();
    }
}
// END
