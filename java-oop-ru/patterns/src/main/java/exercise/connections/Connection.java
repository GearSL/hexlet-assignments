package exercise.connections;

public interface Connection {
    // BEGIN
    void connect();
    void write(String data);
    void disconnect();
    String getCurrentState();
    // END
}
