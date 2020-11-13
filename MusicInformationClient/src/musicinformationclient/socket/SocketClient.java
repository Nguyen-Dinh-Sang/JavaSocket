package musicinformationclient.socket;

import musicinformationclient.ase.ASE;
import musicinformationclient.rsa.RSA;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketClient {
    private Socket socket;
    private Result result;
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private RSA rsa;
    private ASE ase;

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

            rsa = new RSA();
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            sendKeyRSA();
            listening();
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
            byte[] data = ase.maHoa(message);
            if (data != null) {
                dataOutputStream.writeInt(data.length);
                dataOutputStream.write(data);
                dataOutputStream.flush();
                listening();
            }
        } catch (IOException ex) {
            closeSocket();
        }
    }

    private void listening() {
        try {
            byte[] data = new byte[dataInputStream.readInt()];
            dataInputStream.readFully(data);
            xuLy(data);
        } catch (IOException e) {
            closeSocket();
        }
    }

    public interface Result {

        void result(String mes);

        void closed();
    }

    public void sendKeyRSA() {
        try {
            dataOutputStream.writeInt(rsa.getPubKey().length);
            dataOutputStream.write(rsa.getPubKey());
            dataOutputStream.flush();
        } catch (IOException ex) {
            closeSocket();
        }
    }

    public void xuLy(byte[] data) {
        String message = "";
        if (ase != null) {
            message = ase.giaMa(data);
        }

        if (message.startsWith("RESULT###")) {
            result.result(message);
        } else {
            ase = new ASE(rsa.giaMa(data));
        }
    }
}
