package com.lab2.node;

import java.net.InetSocketAddress;

public class NodeApp {
    public static void main(String[] args) throws InterruptedException {
        new InformationalNode( "conf0.xml", new Node(new InetSocketAddress("127.0.0.1", 7000))).run();
        new InformationalNode( "conf1.xml", new Node(new InetSocketAddress("127.0.0.1", 7001))).run();
        new InformationalNode( "conf2.xml", new Node(new InetSocketAddress("127.0.0.1", 7002))).run();
        new InformationalNode( "conf3.xml", new Node(new InetSocketAddress("127.0.0.1", 7003))).run();
        new InformationalNode( "conf4.xml", new Node(new InetSocketAddress("127.0.0.1", 7004))).run();
        new InformationalNode( "conf5.xml", new Node(new InetSocketAddress("127.0.0.1", 7005))).run();
    }
}
