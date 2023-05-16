package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Disconnected implements Connection{
    private final TcpConnection connection;
    public Disconnected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        connection.setState(new Connected(connection));
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
    public String getName() {
        return "disconnected";
    }
}
// END
