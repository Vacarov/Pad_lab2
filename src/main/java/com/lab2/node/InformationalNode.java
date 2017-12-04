package com.lab2.node;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab2.protocols.TCPServer;
import com.lab2.protocols.UDP;
import com.lab2.util.XMLParser;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class InformationalNode {

    private File dataFile;
    private File configFile;
    private Node node;

    public InformationalNode(String dataFile, String configFile, Node node) {
        String filePath = "/home/vvacarov/IdeaProjects/pad_lab2/";
        this.dataFile = new File(filePath + dataFile);
        this.configFile = new File(filePath + configFile);
        this.node = node;
    }

    public void run() {
        try {
            ArrayList<Node> nodes = XMLParser.getNodeList(this.configFile);
            ArrayList<InetSocketAddress> addresses = new ArrayList<>();

            for (Node nod : nodes) {
                if (nod.getLocation().equals(this.node.getLocation())) {
                } else {
                    addresses.add(nod.getLocation());
                }
            }

            this.node.setLinksAdresses(addresses);
            this.node.setLinksNumber(addresses.size());
            this.node.setEmployees(this.getEmployees());

            UDP udp = new UDP();
            udp.sendInfo(this.node);

            new Thread(() -> new TCPServer(this.node).start()).start();
            Thread.sleep(200);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> getEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            employees = mapper.readValue(
                    new File(String.valueOf(this.dataFile)),
                    new TypeReference<List<Employee>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
}
