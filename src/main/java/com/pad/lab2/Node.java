package com.pad.lab2;

import java.io.Serializable;
import java.net.InetSocketAddress;

public class Node implements Serializable{
    private static final long serialVersionUID = 1L;
    private  InetSocketAddress location;

    public Node() {
    }

    public Node(InetSocketAddress location) {
        this.location = location;
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
}
