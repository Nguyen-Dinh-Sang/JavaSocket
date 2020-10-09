/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicinformationclient;

import musicinformationclient.socket.SocketClient;

import java.util.Scanner;

/**
 * @author DinhSang
 */
public class MusicInformationClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SocketClient client = new SocketClient("127.0.0.1", 5000, new SocketClient.Result() {
            @Override
            public void result(String mes) {
                System.out.println(mes);
            }

            @Override
            public void closed() {
                System.out.println("Disconnect");
            }
        });


        Scanner scanner = new Scanner(System.in);
        String line = "";
        while (!line.equals("Over")) {
            line = scanner.nextLine();
            client.send(line);
        }

        client.closeSocket();
    }

}
