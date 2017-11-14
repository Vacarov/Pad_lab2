package com.lab2;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
    private InetSocketAddress location;
    private static ArrayList<Node> nodesLinks = new ArrayList<>();
    private int linksNumber;


    public Node() {
    }

    public Node(InetSocketAddress location) {
        this.location = location;
    }

    public Node(InetSocketAddress location, int linksNumber) {
        this.location = location;
        this.linksNumber = linksNumber;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public InetSocketAddress getLocation() {
        return location;
    }

    public void setLocation(InetSocketAddress location) {
        this.location = location;
    }

    public int getLinksNumber() {
        return linksNumber;
    }

    public void setLinksNumber(int linksNumber) {
        this.linksNumber = linksNumber;
    }

    public static ArrayList<Node> getNodesLinks() {
        return nodesLinks;
    }

    public static void setNodesLinks(ArrayList<Node> nodesLinks) {
        Node.nodesLinks = nodesLinks;
    }

    @Override
    public String toString() {
        return "Node{" +
                "location=" + location +
                ", linksNumber=" + linksNumber +
                '}';
    }
}
