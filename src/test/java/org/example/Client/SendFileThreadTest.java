package org.example.Client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

class SendFileThreadTest {



    @Test
    void test_SOUT_FileNotPresent() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8082);

        String fileName = "2.txt";
        SendFileThread sendFileThread = new SendFileThread(fileName);
        sendFileThread.start();

        serverSocket.close();
    }
    @Test
    void test_SOUT_FileSent() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8082);

        String fileName = "file_for_test.txt";
        SendFileThread sendFileThread = new SendFileThread(fileName);
        sendFileThread.start();

        serverSocket.close();
    }
}