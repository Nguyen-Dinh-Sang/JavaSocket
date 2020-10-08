package musicinformationclient.socket;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    private Socket socket;
    private BufferedWriter out;
    private BufferedReader in;
    private Result result;
    private String address;
    private int port;

    public SocketClient(String address, int port, Result result) {
        this.result = result;
        this.address = address;
        this.port = port;
        openSocket(address, port);
    }

    private void openSocket(String address, int port) {
        try {
            socket = new Socket(address, port);

            if (socket.isConnected()) {
                System.err.println("Connected to server");
            }

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException ex) {
            System.err.println("Lost connection");
            System.out.println("Reconnection");
            openSocket(address, port);
        }
    }

    public void closeSocket() {
        System.out.println("Closing connection");
        try {
            in.close();
            out.close();
            socket.close();
            System.err.println("Connection closed");
            result.closed();
        } catch (IOException ex) {

        }
    }

    public void send(String message) {
        try {
            System.out.println("Send: " + message);
            out.write(message);
            out.newLine();
            out.flush();
            listening();
        } catch (IOException ex) {
            closeSocket();
        }
    }

    private void listening() {
        String line = null;
        try {
            line = in.readLine();
            if (line != null) {
                System.err.println("Result: " + line);
                result.result(line);
            }
        } catch (IOException e) {
            closeSocket();
        }
    }

    public interface Result {

        void result(String mes);

        void closed();
    }
}
