package musicinformationserver.socket;

import musicinformationserver.ByteUtil;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerThread implements Runnable {
    private Socket socket;
    private int clientCount;
    private Thread worker;
    private AtomicBoolean running = new AtomicBoolean(false);
    private InputStream inputStream;
    private OutputStream outputStream;

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
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();
            } catch (IOException e) {
            }

            while (running.get()) {
                try {
                    byte[] data = new byte[1024*64];
                    inputStream.read(data);
                    String message = ByteUtil.getStringUTF16(data);

                    System.out.println("Server received " + ": " + message);

                    String result = "OK client " + clientCount;
                    byte[] dataResult = ByteUtil.getByteUTF16(result);
                    outputStream.write(dataResult, 0, dataResult.length);
                    outputStream.flush();
                } catch (IOException e) {
                    System.err.println(clientCount +  " Lost connection");
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
                inputStream.close();
                outputStream.close();
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
