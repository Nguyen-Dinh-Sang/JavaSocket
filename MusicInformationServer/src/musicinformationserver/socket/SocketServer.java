package musicinformationserver.socket;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class SocketServer {
    private ServerSocket server;
    private List<Thread> clients = new ArrayList<>();

    public SocketServer(int port) {
        openSocket(port);
    }

    private void openSocket(int port) {
        try {
            server = new ServerSocket(port);
            System.err.println("Server started");
            int clientCount = 1;
            System.err.println("Waiting for a client ... " + clientCount);
            while (true) {
                ServerThread serverThread = new ServerThread(server.accept(), clientCount);
                Thread thread = new Thread((serverThread));
                clients.add(thread);
                thread.start();
                clientCount++;
                System.err.println("Waiting for a client ... " + clientCount);
            }
        } catch (IOException ex) {
        }
    }

    public void closeServer() {
        try {
            server.close();
            for (Thread thread : clients) {
            }
            System.out.println("Closing connection");
        } catch (IOException ex) {
        }
    }
}
