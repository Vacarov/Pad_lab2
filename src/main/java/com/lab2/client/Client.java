package com.lab2.client;

import com.lab2.protocols.TCPCommunication;

public class Client {
    private TCPCommunication tcpCommunication;

    public Client() {
    }

    public Client(TCPCommunication tcpCommunication) {
        this.tcpCommunication = tcpCommunication;
    }
}
