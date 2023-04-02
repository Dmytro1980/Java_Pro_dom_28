package org.example.Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String message;

        try (Socket socket = new Socket("localhost", 8081)) {

            ClientThread clientThread = new ClientThread(socket);
            new Thread(clientThread).start();

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("Write your message here:");

            while (true) {

                message = scanner.nextLine();
                if (message.equals("exit")) {
                    printWriter.println(message);
                    break;
                }

                if (message.startsWith("file")){
                    String fileName = message.substring(5);
                    Thread thread = new Thread(new SendFileThread(fileName));
                    thread.start();
                    continue;
                }
                printWriter.println(message);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
