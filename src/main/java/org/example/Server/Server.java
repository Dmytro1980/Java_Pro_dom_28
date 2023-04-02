package org.example.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Server {

    private static List<ClientConnectionInfo> activeConnectionList = new ArrayList<>();

    private static List<ServerThread> serverThreads = new ArrayList<>();

    public static List<ServerThread> getServerThreads() {
        return serverThreads;
    }

    public static List<ClientConnectionInfo> getActiveConnectionList() {
        return activeConnectionList;
    }

    public static void main(String[] args) throws IOException {

        int clientCounter = 0;
        ServerSocket serverSocket = new ServerSocket(8081);
        System.out.println("Server started.");
        printClientListInfo();

        // thread1 - "приёмник" файлов
        GetFileThread getFileThread = new GetFileThread();
        Thread thread1 = new Thread(getFileThread);
        thread1.start();

        while (true) {

            Socket socket = serverSocket.accept();
            clientCounter++;

            addNewClientToList(clientCounter, socket);
            printClientListInfo();
            System.out.println("Client-" + clientCounter + " connected.");

            // создание объекта ServerThread, передача в поток индекса
            // добавление объекта ServerThread в list, создание нового потока, его запуск,
            ServerThread serverThread = new ServerThread(socket);
            serverThreads.add(serverThread);
            Thread thread = new Thread(serverThread);
            thread.start();

            // рассылка сообщения о подключении нового клиента
            ServerThread.sendMessageToAllClients("!!! Client-" + clientCounter
                    + " connected to server.", socket);
        }
    }

    public static void printClientListInfo() {
        System.out.println("Now " + activeConnectionList.size() + " clients in list");
        for (ClientConnectionInfo cci : activeConnectionList) {
            if (cci != null) {
                System.out.println(cci);
            }
        }
    }

    // добавдение инфы о соединениии в список
    public static void addNewClientToList(int clientCounter, Socket socket) {
        activeConnectionList.add(new ClientConnectionInfo(clientCounter, socket));
    }

    // метод удаляющий запись в activeConnectionList по socket
    public static void deleteClientFromListBySocket(Socket socket) {
        ListIterator<ClientConnectionInfo> iterator = activeConnectionList.listIterator();
        while (iterator.hasNext()){
            if(iterator.next().getSocket().equals(socket)){
                iterator.remove();
            }
        }
        }
}
