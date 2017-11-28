package com.lab2.protocols;

import com.google.gson.Gson;
import com.lab2.common.Message;
import com.lab2.mediator.ClientConnection;
import com.lab2.node.Node;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer {
    private ServerSocket serverSocket;
    private Node node;
    private static ArrayList<InetSocketAddress> registeredNodes = new ArrayList<>();

    public TCPServer(int port) {
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TCPServer(Node node) {
        this.node = node;
        try {
            this.serverSocket = new ServerSocket(this.node.getLocation().getPort());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void acceptClients(Node bestNode) {
        System.out.println("I am waiting for clients...");
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> new ClientConnection(clientSocket, bestNode).start()).start();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void start() {
        try {
            while (true) {
//                ArrayList<ArrayList<Employee>> employees = new ArrayList<>();
                Socket clientSocket = serverSocket.accept();
                System.out.println("You are connected to " + serverSocket.getLocalPort());

                registeredNodes.add(this.node.getLocation());
                for (InetSocketAddress socketAddress : this.node.getLinksAdresses()) {
                    if (!registeredNodes.contains(socketAddress)) {
                        TCPCommunication tcpCommunication = new TCPCommunication();
                        tcpCommunication.startConnection(socketAddress.getHostName(), socketAddress.getPort());
                    }
                }

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                Gson gson = new Gson();
                final String json = gson.toJson(this.node.getEmployees());
                System.out.println(json);
                out.println("hello");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
