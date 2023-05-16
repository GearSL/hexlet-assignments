package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private final TcpConnection connection;

    public Connected(TcpConnection connection) {
        this.connection = connection;
    }

    @Override
    public void connect() {
        System.out.println("Error, already connected!");
    }

    @Override
    public void write(String data) {
        System.out.println("success written");
    }

    @Override
    public void disconnect() {
        connection.setState(new Disconnected(connection));
    }

    @Override
    public String getName() {
        return "connected";
    }
}
// END
