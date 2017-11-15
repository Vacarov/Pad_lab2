package com.lab2.mediator;

import com.lab2.mediator.ProxyServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ProxyServer proxyServer = new ProxyServer();
        proxyServer.start();
    }
}
