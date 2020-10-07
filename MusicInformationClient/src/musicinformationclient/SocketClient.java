package musicinformationclient;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    private Socket socket = null;
    private BufferedWriter out = null;
    private BufferedReader in = null;
    private Result result;

    public SocketClient(String address, int port, Result result) {
        this.result = result;
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
        }
    }

    public void closeSocket() {
        try {
            in.close();
            out.close();
            socket.close();
            System.err.println("Closing connection");
        } catch (IOException ex) {
        }
    }

    public void send(String message) {
        try {
            System.out.println("send: " + message);
            out.write(message);
            out.newLine();
            out.flush();
            listening();
        } catch (IOException ex) {
        }
    }

    private void listening(){
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (line != null) {
            System.err.println("Result: " + line);
            result.result(line);
        }
    }

    public interface Result {

        void result(String mes);
    }
}
