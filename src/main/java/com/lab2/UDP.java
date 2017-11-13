package com.lab2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;

public class UDP {
    private int mcPort = 12345;
    private String mcIP = "230.1.1.1";

    public ArrayList<Node> receiveInfoAboutRunningNodes() throws IOException {
        ArrayList<Node> nodes = new ArrayList<>();
        MulticastSocket mcSocket = new MulticastSocket(this.mcPort);
        InetAddress mcIPAddress = InetAddress.getByName(this.mcIP);

        System.out.println("Multicast Receiver running at:" + mcSocket.getLocalSocketAddress());
        mcSocket.joinGroup(mcIPAddress);
        System.out.println("Waiting for a  multicast info about existent nodes...");

        DatagramPacket receivePacket;
        mcSocket.setSoTimeout(5000);

        try {
            while (mcSocket.getSoTimeout() > 0) {
                System.out.println("Wating for datagram to be received...");

                //Create buffer
                byte[] buffer = new byte[1024];
                mcSocket.receive(new DatagramPacket(buffer, 1024));
                System.out.println("Datagram received!");

                //Deserialze object
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                ObjectInputStream ois = new ObjectInputStream(bais);
                try {
                    Object readObject = ois.readObject();
                    if (readObject instanceof Node) {
                        Node node = (Node) readObject;
                        System.out.println("Received node info: " + node.toString());
                    } else {
                        System.out.println("The received object is not of type String!");
                    }
                } catch (Exception e) {
                    System.out.println("No object could be read from the received UDP datagram.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mcSocket.leaveGroup(mcIPAddress);
            mcSocket.close();
        }

        System.out.println(nodes);
        return nodes;
    }

    public void sendInfo(Node node) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(node);
        oos.flush();
        // get the byte array of the object
        byte[] buf= baos.toByteArray();


        DatagramSocket udpSocket = new DatagramSocket();
        InetAddress mcIPAddress = InetAddress.getByName(this.mcIP);

//        byte[] msg = node.toString().getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        packet.setAddress(mcIPAddress);
        packet.setPort(this.mcPort);
        udpSocket.send(packet);

        System.out.println("Sent a  multicast message.");
        System.out.println("Exiting application");
        udpSocket.close();
    }

}
