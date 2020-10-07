package musicinformationserver.socket;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable {
    private Socket socket;
    private int clientCount;
    BufferedWriter out = null;
    BufferedReader in = null;

    public ServerThread(Socket socket, int clientCount) {
        this.socket = socket;
        this.clientCount = clientCount;
    }

    @Override
    public void run() {
        if (socket != null) {
            System.err.println("Client " + clientCount + " accepted");

            try {
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
            }

            while (true) {
                String data;
                try {
                    data = in.readLine();
                    System.out.println("Server received " + clientCount + ": " + data);
                    out.write("ok");
                    out.newLine();
                    out.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void stopThread() {

    }
}
