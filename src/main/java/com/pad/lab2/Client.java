package com.pad.lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;

    public void startConnection(String ip, int port) throws Exception {

    }

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 4444);
        System.out.println("This is sender from port: " + clientSocket.getLocalPort());
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    }
}