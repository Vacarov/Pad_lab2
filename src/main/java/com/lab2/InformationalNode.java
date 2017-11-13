package com.lab2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InformationalNode extends Thread {

    private Node node;
    private File file;

    public InformationalNode(Node node, String fileName) {
        this.node = node;
        String filePath = "/home/vvacarov/IdeaProjects/pad_lab2/";
        this.file = new File(filePath + fileName);
    }

    @Override
    public void run() {
        try{
        UDP udp = new UDP();
        udp.sendInfo(this.node);
//        System.out.println(this.getEmployees().toString());
        }
        catch (Exception e){
            e.printStackTrace();
        }
//        new TcpServer().start();

    }

    public ArrayList<Employee> getEmployees() {
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            employees = mapper.readValue(
                    new File(String.valueOf(this.file)),
                    new TypeReference<List<Employee>>() {
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }
}
