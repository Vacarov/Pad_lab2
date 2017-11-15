package com.lab2.client;

import com.lab2.common.Message;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.startConnection("127.0.0.1",5555);

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String text = scanner.next();
            Message message = new Message("asnd",text);
            client.sendMessage(message);
        }
    }
}
