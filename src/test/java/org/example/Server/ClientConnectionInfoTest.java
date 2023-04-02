package org.example.Server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;

class ClientConnectionInfoTest {

    @Test
    void clientConnectionCreated() {
        int connectionNumber = 1;
        Socket socket = new Socket();
        // время тестируется без секунд
        String currentDateTime =  new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date());

        ClientConnectionInfo clientConnectionInfo = new ClientConnectionInfo(connectionNumber, socket);

        try{
            Assertions.assertNotNull(clientConnectionInfo);
            Assertions.assertEquals("Client-1", clientConnectionInfo.getConnectionName());
            Assertions.assertEquals(socket, clientConnectionInfo.getSocket());
            Assertions.assertTrue(clientConnectionInfo.getConnectTime().contains(currentDateTime));
        }catch (AssertionError e){
            throw e;
        }finally {
            System.out.println("Object clientConnectionInfo is created: " + clientConnectionInfo + "\n");
        }

    }

}