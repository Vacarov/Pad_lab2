package com.lab2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InformationalNode extends Thread {

    private File dataFile;
    private File configFile;
    private Node node;

    public InformationalNode(String dataFile, String configFile, Node node) {
        String filePath = "/home/vvacarov/IdeaProjects/pad_lab2/";
        this.dataFile = new File(filePath + dataFile);
        this.configFile = new File(filePath + configFile);
        this.node = node;
    }

    @Override
    public void run() {
        try {
            ArrayList<Node> nodes = XMLParser.getNodeList(this.configFile);
            this.node.setLinksNumber(nodes.size());

            UDP udp = new UDP();
            udp.sendInfo(this.node);

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
