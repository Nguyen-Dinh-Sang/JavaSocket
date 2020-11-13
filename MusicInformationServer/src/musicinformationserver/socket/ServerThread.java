package musicinformationserver.socket;

import musicinformationserver.service.Service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServerThread implements Runnable, Service.TuongTac {

    private Socket socket;
    private int clientCount;
    private Thread worker;
    private AtomicBoolean running = new AtomicBoolean(false);
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private Service service;

    public ServerThread(Socket socket, int clientCount) {
        this.socket = socket;
        this.clientCount = clientCount;
        service = new Service(this);
    }

    @Override
    public void run() {
        running.set(true);
        if (socket != null) {
            System.err.println("Client " + clientCount + " accepted");

            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
            }

            while (running.get()) {
                try {
                    byte[] data = new byte[dataInputStream.readInt()];
                    dataInputStream.readFully(data);
                    service.xyLy(data);
                } catch (IOException e) {
                    System.err.println(clientCount + " Lost connection");
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
                dataOutputStream.close();
                dataInputStream.close();
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

    @Override
    public void send(byte[] data) {
        try {
            dataOutputStream.writeInt(data.length);
            dataOutputStream.write(data);
            dataOutputStream.flush();
        } catch (IOException e) {
            System.err.println(clientCount + " Lost connection");
            closeServerThread();
        }
    }

    @Override
    public void closeThread() {
        this.closeServerThread();
    }
}
