package com.lab2;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public class NodeApp {
    public static void main(String[] args) throws InterruptedException {
        new InformationalNode("employees0", "conf0.xml", new Node(new InetSocketAddress("127.0.0.1", 7000))).run();
        new InformationalNode("employees1", "conf1.xml", new Node(new InetSocketAddress("127.0.0.1", 7001))).run();
        new InformationalNode("employees2", "conf2.xml", new Node(new InetSocketAddress("127.0.0.1", 7002))).run();
        new InformationalNode("employees3", "conf3.xml", new Node(new InetSocketAddress("127.0.0.1", 7003))).run();
        new InformationalNode("employees4", "conf4.xml", new Node(new InetSocketAddress("127.0.0.1", 7004))).run();
        new InformationalNode("employees5", "conf5.xml", new Node(new InetSocketAddress("127.0.0.1", 7005))).run();
    }
}
