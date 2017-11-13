package com.pad.lab2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerTCP extends Thread{
    private ServerSocket serverSocket;
    private int port;
    private Node node;

    public ServerTCP(Node node) throws IOException {
        this.port = node.getLocation().getPort();
        this.serverSocket = new ServerSocket(port);
    }

//    @Override
//    public void run() {
//        System.out.println("fuck");
////        ArrayList<Node> nodes = new ArrayList<Node>();
//        try {
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("Client with port "+ clientSocket.getLocalPort() +"is connected on port " + port + "...");
//
//                new Thread(() -> {
//                    new ClientConnection(clientSocket).start();
//                }).start();
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
