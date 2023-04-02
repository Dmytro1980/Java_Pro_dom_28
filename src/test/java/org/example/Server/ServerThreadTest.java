package org.example.Server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.Socket;

import org.junit.jupiter.api.Assertions;

class ServerThreadTest {

    @Test
    void serverThreadCreated(){
        Socket socket = new Socket();
        int connectionNumber = 1;
        Server.addNewClientToList(connectionNumber, socket);
        ServerThread serverThread = new ServerThread(socket);

        try{
            Assertions.assertNotNull(serverThread);
            Assertions.assertEquals("Client-1", serverThread.getCurrentClientName());
        }catch (AssertionError e){
            throw e;
        }finally {
            System.out.println("Object serverThread is created: " + serverThread + "\n");
        }
    }

}