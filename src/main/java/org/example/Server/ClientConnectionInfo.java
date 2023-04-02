package org.example.Server;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientConnectionInfo {
    private String connectionName;
    private String connectTime;
    private Socket socket;

    public ClientConnectionInfo(int connectionNumber, Socket socket/*, Thread thread*/) {
        this.connectionName = "Client" + "-" + connectionNumber;
        this.connectTime = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
        this.socket = socket;
    }

    public String getConnectionName() {
        return connectionName;
    }

    public String getConnectTime() {
        return connectTime;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    @Override
    public String toString() {
        return "ClientConnectionInfo {" +
                "connectionName='" + connectionName + '\'' +
                ", connectTime='" + connectTime + '\'' +
                ", socket=" + socket +
                '}';
    }
}
