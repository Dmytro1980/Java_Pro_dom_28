package org.example.Client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

class ClientThreadTest {

    ServerSocket serverSocket;
    Socket socket1;
    Socket socket2;

    {
        try {
            serverSocket = new ServerSocket(8081);
            socket1 = new Socket("localhost", 8081);
            socket2 = new Socket("localhost", 8081);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void testConstructor() throws IOException {
        BufferedReader streamIn = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
        Assertions.assertEquals(streamIn.getClass(), new ClientThread(socket2).getStreamIn().getClass());
    }


}