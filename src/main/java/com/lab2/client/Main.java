package com.lab2.client;

import com.lab2.common.Message;
import com.lab2.protocols.TCPCommunication;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        TCPCommunication tcpCommunication = new TCPCommunication();
        tcpCommunication.startConnection("127.0.0.1",5555);

        while (true) {

            System.out.println(" 1. GET_ALL");
            System.out.println(" 2. SORT");
            System.out.println(" 3. FILTER");
            System.out.println(" select one choice");

            Integer choice = 0;
            Scanner scan = new Scanner(System.in);
            do {
                choice = Integer.parseInt(scan.nextLine());
                switch (choice) {
                    case 1:
                        Message message1 = new Message("GET_ALL");
                        tcpCommunication.sendMessage(message1);
                        break;
                    case 2:
                        System.out.println("Type by each field: name/surname/salary");
                        Scanner scan2 = new Scanner(System.in);
                        String field = scan2.next();
                        Message message2 = new Message("SORT",field);
                        tcpCommunication.sendMessage(message2);
                        break;
                    case 3:
                        Scanner scan3 = new Scanner(System.in);
                        System.out.println("Type by each field: name/surname/salary");
                        String field3 = scan3.next();
                        System.out.println("Type text for search:");
                        String text = scan3.next();
                        Message message3 = new Message("FILTER",field3, text);
                        tcpCommunication.sendMessage(message3);
                        break;
                }
            } while (!choice.equals(-1));
        }
    }
}
