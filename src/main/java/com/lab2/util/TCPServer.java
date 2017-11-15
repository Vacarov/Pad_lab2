package com.lab2.util;

import com.lab2.client.ClientConnection;

import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    private ServerSocket serverSocket;

    public TCPServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void acceptClients(){
        System.out.println("I am waiting for clients...");
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> {
                    new ClientConnection().start();
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
