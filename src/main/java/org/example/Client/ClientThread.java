package org.example.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

// класс ClientThread - для прослушивания ответа от сервера
// без блокировки при чтении со сканера.

public class ClientThread extends Thread {

    public BufferedReader getStreamIn() {
        return streamIn;
    }

    private BufferedReader streamIn;

    public ClientThread(Socket socket) throws IOException {
        streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            while (true){
                String message = streamIn.readLine();
                System.out.println(message);
            }
        } catch (SocketException e) {
            System.out.println("Client is disconnected.");
        } catch (IOException e){
            System.out.println(e);
        } finally {
            try {
                streamIn.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
