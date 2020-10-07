package musicinformationserver.socket;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerThread implements Runnable {
    private Socket socket;
    private int clientCount;
    private BufferedWriter out;
    private BufferedReader in;
    private Thread worker;
    private AtomicBoolean running = new AtomicBoolean(false);

    public ServerThread(Socket socket, int clientCount) {
        this.socket = socket;
        this.clientCount = clientCount;
    }

    @Override
    public void run() {
        running.set(true);
        if (socket != null) {
            System.err.println("Client " + clientCount + " accepted");

            try {
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
            }

            while (running.get()) {
                String data;
                try {
                    data = in.readLine();
                    System.out.println("Server received " + clientCount + ": " + data);
                    out.write("ok");
                    out.newLine();
                    out.flush();
                } catch (IOException e) {
                    System.err.println("Lost connection");
                    closeServerThread();
                }
            }
        }
    }

    public void closeServerThread() {
        if (running.get()) {
            System.out.println("Closing thread " + clientCount);

            running.set(false);
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
            }
        }
        System.err.println("Thread " + clientCount + " closed");
    }

    public void startServerThread() {
        worker = new Thread(this);
        worker.start();
    }
}
