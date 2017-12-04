package com.lab2.protocols;

import com.lab2.common.Message;
import com.lab2.mediator.ClientConnection;
import com.lab2.node.Command;
import com.lab2.node.Employee;
import com.lab2.node.Node;

import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class TCPServer extends Thread {
    private ServerSocket serverSocket;
    private Node node;

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
        ArrayList<InetSocketAddress> registeredNodes = new ArrayList<>();
//         ArrayList<Node> registeredNodes = new ArrayList<>();
//         ArrayList<Node> nodes =new ArrayList<>();

        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("You are connected to " + serverSocket.getLocalPort());

                TCPCommunication clientTcpCommunication = new TCPCommunication();
                Message clientRequest = clientTcpCommunication.receiveMessage(clientSocket);
                System.out.println(clientRequest);


//                registeredNodes.add(this.node.getLocation());
//                for (InetSocketAddress socketAddress : this.node.getLinksAdresses()) {
//                    if (this.node.getLinksAdresses().contains(node.getLocation())) {
//                        TCPCommunication tcpCommunication = new TCPCommunication();
//                        tcpCommunication.startConnection(node.getLocation().getHostName(), node.getLocation().getPort());
//                        System.out.println(node.getEmployees());
//                    }
//                }
                registeredNodes.add(this.node.getLocation());
                for (InetSocketAddress socketAddress : this.node.getLinksAdresses()) {
                    if (!registeredNodes.contains(socketAddress)) {
                        TCPCommunication tcpCommunication = new TCPCommunication();
                        tcpCommunication.startConnection(socketAddress.getHostName(), socketAddress.getPort());

                        System.out.println(this.node.getEmployees());
                    }
                }

                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                Command response = new Command();

                ArrayList<Employee> employees = this.node.getEmployees();

                String message = null;
                if (clientRequest.getCommand().equals("GET_ALL")) {
                    message = response.getAll(employees);
                } else if (clientRequest.getCommand().equals("SORT")) {
                    message = response.getSortedEmployees(employees, clientRequest);
                }

                out.println(message);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
