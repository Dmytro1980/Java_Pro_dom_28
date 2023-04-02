package org.example.Server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;


class ServerTest {

    @Test
    void Assert_AddClientConnectionInfoToList() throws IOException {
        int clientCounter = 1;
        Socket socket = new Socket();

        Server.addNewClientToList(clientCounter, socket);
        Assertions.assertEquals(1, Server.getActiveConnectionList().size());

    }

    @Test
    void DeleteClientConnectionInfoFromList() throws IOException {
        int clientCounter1 = 1;
        int clientCounter2 = 2;
        Socket socket1 = new Socket();
        Socket socket2 = new Socket();

        Server.addNewClientToList(clientCounter1, socket1);
        Server.addNewClientToList(clientCounter2, socket2);

        Assertions.assertEquals(2, Server.getActiveConnectionList().size());

        Server.deleteClientFromListBySocket(socket1);

        Assertions.assertEquals(1, Server.getActiveConnectionList().size());

        Server.deleteClientFromListBySocket(socket2);
    }

}