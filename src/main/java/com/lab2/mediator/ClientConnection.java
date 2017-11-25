package com.lab2.mediator;

import com.lab2.common.Message;
import com.lab2.node.Node;
import com.lab2.protocols.TCPCommunication;

import java.io.IOException;
import java.io.PrintWriter;
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
            Message clientRequest = clientTcpCommunication.receiveMessage(clientSocket);
            Socket clSocket = new Socket(this.node.getLocation().getAddress(), this.node.getLocation().getPort());
            System.out.println(clientRequest.toString());

            System.out.println("This client is on port: " + clSocket.getLocalPort());
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println(clientRequest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
