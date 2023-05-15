package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection{
    private final TcpConnection tcp;
    public Disconnected(TcpConnection tcp) {
        this.tcp = tcp;
    }

    @Override
    public void connect() {
        System.out.println("connected");
    }

    @Override
    public void write(String data) {
        System.out.println("Error, writing not available while disconnected");
    }

    @Override
    public void disconnect() {
        System.out.println("Error, already disconnected");
    }

    @Override
    public String getCurrentState() {
        return "disconnected";
    }
}
// END
