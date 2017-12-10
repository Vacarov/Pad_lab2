package com.lab2.node;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab2.common.Employee;
import com.lab2.protocols.TCPServer;
import com.lab2.protocols.UDP;
import com.lab2.util.XMLParser;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class InformationalNode {

    private File configFile;
    private Node node;

    public InformationalNode(String configFile, Node node) {
        this.configFile = new File("/home/vvacarov/IdeaProjects/pad_lab2/" + configFile);
        this.node = node;
    }

    public void run() {
        try {
            ArrayList<Node> nodes = XMLParser.getNodeList(this.configFile);
            ArrayList<InetSocketAddress> addresses = new ArrayList<>();

            ArrayList<Node> nods = new ArrayList<>();
            for (Node nod : nodes) {
                int a = Integer.parseInt(nod.getLocation().toString().substring(nod.getLocation().toString().length() - 1));
                if (nod.getLocation().equals(this.node.getLocation())) {
                    this.node.setEmployees(this.getNodeEmployees(a));
                } else {
                    addresses.add(nod.getLocation());
                    nod.setEmployees(this.getNodeEmployees(a));
                    nods.add(nod);
                }
            }

            this.node.setLinksAdresses(addresses);
            this.node.setLinksNumber(addresses.size());

            UDP udp = new UDP();
            udp.sendInfo(this.node);

            new Thread(() -> new TCPServer(this.node).start(nods)).start();
            Thread.sleep(200);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Employee> getNodeEmployees(int a) {
        String dataFile = "/home/vvacarov/IdeaProjects/pad_lab2/employees" + a;
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            employees = mapper.readValue(
                    new File(String.valueOf(dataFile)),
                    new TypeReference<List<Employee>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
}
