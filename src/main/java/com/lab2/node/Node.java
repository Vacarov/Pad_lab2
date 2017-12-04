package com.lab2.node;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
    private InetSocketAddress location;
    private int linksNumber;
    private ArrayList<Employee> employees;
    private ArrayList<InetSocketAddress> linksAdresses;


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

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<InetSocketAddress> getLinksAdresses() {
        return linksAdresses;
    }

    public void setLinksAdresses(ArrayList<InetSocketAddress> linksAdresses) {
        this.linksAdresses = linksAdresses;
    }

    @Override
    public String toString() {
        return "Node{" +
                "location=" + location +
                ", linksNumber=" + linksNumber +
                '}';
    }
}
