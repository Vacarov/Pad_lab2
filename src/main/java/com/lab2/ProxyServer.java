package com.lab2;

import java.io.IOException;

public class ProxyServer {
    public static void main(String[] args) throws IOException {
        UDP udp = new UDP();
//        udp.receiveInfoAboutRunningNodes();
        System.out.println("hello");
        System.out.println(udp.receiveInfoAboutRunningNodes().toString());
    }
}
