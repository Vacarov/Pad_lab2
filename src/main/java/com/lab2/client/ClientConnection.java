package com.lab2.client;

public class ClientConnection extends Thread {
    @Override
    public synchronized void start() {
        System.out.println("client connection");
    }
}
