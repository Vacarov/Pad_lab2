package com.lab2.client;

import com.lab2.common.Message;
import com.lab2.protocols.TCPCommunication;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        TCPCommunication tcpCommunication = new TCPCommunication();
        tcpCommunication.startConnection("127.0.0.1",5555);

//        Client client = new Client(tcpCommunication);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.next();

            Message message = new Message("asd",text);
            tcpCommunication.sendMessage(message);

//            client.sendMessage(message);
        }
    }
}
