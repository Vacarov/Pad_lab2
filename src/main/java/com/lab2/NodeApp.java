package com.lab2;

import java.net.InetSocketAddress;
import java.util.ArrayList;

public class NodeApp {
    public static void main(String[] args) throws InterruptedException {

//        new Thread(() -> {
//            new InformationalNode(
//                    new Node(
//                            new InetSocketAddress(
//                                    "127.0.0.1",4000)),
//                    "employees0");
//        }).start();

        new InformationalNode(
                new Node(
                        new InetSocketAddress(
                                "127.0.0.1", 4000)),
                "employees0").run();
        Thread.sleep(100);

        new InformationalNode(
                new Node(
                        new InetSocketAddress(
                                "127.0.0.1", 4001)),
                "employees1").run();
        Thread.sleep(100);

        new InformationalNode(
                new Node(
                        new InetSocketAddress(
                                "127.0.0.1", 4002)),
                "employees2").run();
        Thread.sleep(100);


//        ArrayList<Node> nodes = new ArrayList<>();
//        ArrayList<InformationalNode> informationalNodes = new ArrayList<>();
//
//        for (int i = 0; i < 3; i++) {
//            Node node = new Node(new InetSocketAddress("127.0.0." + i, 4000));
//            nodes.add(node);
//            informationalNodes.add(new InformationalNode(node,"employees" + i));
//        }
//
//        for (InformationalNode informationalNode : informationalNodes) {
//            System.out.println(informationalNode.getEmployees().toString());
//        }
    }
}
