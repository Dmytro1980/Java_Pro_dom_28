package org.example.Client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class SendFileThread extends Thread{

    private String fileName;

    public SendFileThread(String name) {
        this.fileName = name;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket("localhost", 8082)) {

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            File file = new File(fileName);
            if(!file.isFile()) {
                System.out.println("File is not present. Try again.");
                dataOutputStream.close();
                return;
            }

            FileInputStream fileInputStream = new FileInputStream(file);

            // отправка имени файла
            dataOutputStream.writeUTF(fileName);

            // отправка размер файла
            dataOutputStream.writeLong(file.length());

            byte[] buffer = new byte[4 * 1024];
            int filePart;

            // файл отсылается частями, если он большой
            while ((filePart = fileInputStream.read(buffer)) != -1) {
                // отправка части файла
                dataOutputStream.write(buffer, 0, filePart);
                dataOutputStream.flush();
            }

            System.out.println("File has been sent");

            fileInputStream.close();
            dataOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
