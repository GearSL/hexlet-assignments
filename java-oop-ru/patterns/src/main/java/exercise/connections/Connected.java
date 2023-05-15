package exercise.connections;

import exercise.TcpConnection;

// BEGIN
public class Connected implements Connection {
    private final TcpConnection tcp;

    public Connected(TcpConnection tcp) {
        this.tcp = tcp;
    }

    @Override
    public void connect() {
        System.out.println("Error, already connected! =(");
    }

    @Override
    public void write(String data) {
        System.out.println("success written");
    }

    @Override
    public void disconnect() {
        tcp.disconnect();
        System.out.println("disconnected");
    }

    @Override
    public String getCurrentState() {
        return "connected";
    }
}
// END
