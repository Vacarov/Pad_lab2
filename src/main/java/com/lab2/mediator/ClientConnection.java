package com.lab2.mediator;

import com.lab2.common.Message;
import com.lab2.node.Node;
import com.lab2.protocols.TCPCommunication;

import java.io.*;
import java.net.Socket;

public class ClientConnection extends Thread {
    private Socket clientSocket;
    private Node node;

    public ClientConnection(Socket clientSocket, Node node) {
        this.clientSocket = clientSocket;
        this.node = node;
    }

    @Override
    public synchronized void start() {
        try {
            TCPCommunication clientTcpCommunication = new TCPCommunication();
            String clientRequest = clientTcpCommunication.receiveMessageInJson(clientSocket);
            System.out.println("Client Request is : " + clientRequest);

            Socket nodeSocket = new Socket(this.node.getLocation().getAddress(), this.node.getLocation().getPort());
            PrintWriter outNode = new PrintWriter(nodeSocket.getOutputStream(), true);
            outNode.println(clientRequest);

            BufferedReader inNode = new BufferedReader(new InputStreamReader(nodeSocket.getInputStream()));
            PrintWriter outClient = new PrintWriter(this.clientSocket.getOutputStream(), true);

            String input = inNode.readLine();
            outClient.println(input);

            System.out.println(input);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
