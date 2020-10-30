package musicinformationclient.socket;

import musicinformationclient.ByteUtil;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    private Socket socket;
    private Result result;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;

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

            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.err.println("Lost connection");
            System.out.println("Reconnection");
            openSocket(address, port);
        }
    }

    public void closeSocket() {
        System.out.println("Closing connection");
        try {
            dataInputStream.close();
            dataOutputStream.close();
            socket.close();
            System.err.println("Connection closed");
            result.closed();
        } catch (IOException ex) {

        }
    }

    public void send(String message) {
        try {
            System.out.println("Send: " + message);
            byte[] data = ByteUtil.getByteUTF16(message);
            dataOutputStream.writeInt(data.length);
            dataOutputStream.write(data);
            dataOutputStream.flush();
            listening();
        } catch (IOException ex) {
            closeSocket();
        }
    }

    private void listening() {
        try {
            byte[] data = new byte[dataInputStream.readInt()];
            dataInputStream.readFully(data);
            String message = ByteUtil.getStringUTF16(data);
            result.result(message);
        } catch (IOException e) {
            closeSocket();
        }
    }

    public interface Result {

        void result(String mes);

        void closed();
    }
}
