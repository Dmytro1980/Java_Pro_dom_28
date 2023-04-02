package org.example.Server;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GetFileThread extends Thread {

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(8082)) {

            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            // получение имени файла
            String fileName = dataInputStream.readUTF();

            int nameBegin = fileName.lastIndexOf("/") + 1;
            fileName = fileName.substring(nameBegin);

            // fileName
            System.out.println("fileName=" + fileName);

            int filePart;
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);

            // получение размера файла
            long fileSizeFromClient = dataInputStream.readLong();

            byte[] buffer = new byte[4 * 1024];

            // запись в файл
            while (fileSizeFromClient > 0 &&
                    (filePart = dataInputStream.read(buffer, 0, (int) Math.min(buffer.length, fileSizeFromClient))) != -1) {
                // запись файла в стрим по частям
                fileOutputStream.write(buffer, 0, filePart);
                fileSizeFromClient -= filePart;    // уменьшение размера файлаОтКлиениа на размер скопированной части
            }
            System.out.println("File is Received");
            fileOutputStream.close();

            dataInputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            run();
        }
    }
}
