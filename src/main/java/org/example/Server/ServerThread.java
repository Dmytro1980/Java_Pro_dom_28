package org.example.Server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    private final Socket socket;
    private PrintWriter printWriter;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);

            while (true) {

                String message = streamIn.readLine();
                if (message == null) {
                    continue;
                }
                if (message.equals("exit")) {
                    break;
                }

                if (message.startsWith("file")) {
                    continue;
                }

                // печать на сервере текста от клиента
                System.out.println(getCurrentClientName() + " say: " + message);

                //отправка ответа всем клиентам
                sendMessageToAllClients(getCurrentClientName() + ": " + message, this.socket);
            }

            Server.deleteClientFromListBySocket(this.socket);
            Server.printClientListInfo();

        } catch (RuntimeException | IOException e) {

            Server.deleteClientFromListBySocket(this.socket);
            Server.printClientListInfo();

        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {
            }
        }
    }

    public String getCurrentClientName() {
        String currentClientName = null;
        for (ClientConnectionInfo cci : Server.getActiveConnectionList()) {
            if (cci.getSocket() == this.socket) {
                currentClientName = cci.getConnectionName();
            }
        }
        return currentClientName;
    }

    // отправка ответа всем клиентам
    public static void sendMessageToAllClients(String message, Socket socket) {
        for (ServerThread serverThread : Server.getServerThreads()) {

            if (serverThread.socket != socket) {
                serverThread.printWriter.println(message);
            }
        }
    }
}
