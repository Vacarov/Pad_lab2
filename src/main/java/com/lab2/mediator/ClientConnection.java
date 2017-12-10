package com.lab2.mediator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lab2.common.EFormatType;
import com.lab2.common.Employee;
import com.lab2.common.Message;
import com.lab2.node.Node;
import com.lab2.protocols.TCPCommunication;
import com.lab2.util.XMLParser;
import com.lab2.util.XMLValidator;
import org.xml.sax.SAXException;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnection extends Thread {
    private Socket clientSocket;
    private Node node;

    public ClientConnection(Socket clientSocket, Node node) {
        this.clientSocket = clientSocket;
        this.node = node;
    }

    @Override
    public synchronized void start() {
        try {
            TCPCommunication clientTcpCommunication = new TCPCommunication();
            String clientRequest = clientTcpCommunication.receiveMessageInJson(clientSocket);
            System.out.println("Client Request is : " + clientRequest);

            Socket nodeSocket = new Socket(this.node.getLocation().getAddress(), this.node.getLocation().getPort());
            PrintWriter outNode = new PrintWriter(nodeSocket.getOutputStream(), true);
            outNode.println(clientRequest);

            BufferedReader inNode = new BufferedReader(new InputStreamReader(nodeSocket.getInputStream()));
            PrintWriter outClient = new PrintWriter(this.clientSocket.getOutputStream(), true);

            String input = inNode.readLine();

            Gson gson = new Gson();
            Message message = gson.fromJson(clientRequest, Message.class);
            if (message.getFormatType().equals(EFormatType.JSON)) {
                outClient.println(input);
            } else {
                ArrayList<Employee> employees = gson.fromJson(input, new TypeToken<ArrayList<Employee>>() {
                }.getType());
                XMLParser.writeEmployeesInXmlFile(employees);

                File schemaFile = new File("/home/vvacarov/IdeaProjects/pad_lab2/src/main/java/com/lab2/util/schema/schema.xsd");
                File dataFile = new File("/home/vvacarov/IdeaProjects/pad_lab2/src/main/java/com/lab2/util/data/data.xml");
                XMLValidator XMLValidator = new XMLValidator();
                boolean valid = XMLValidator.validate(dataFile, schemaFile);
                if (valid) {
                    System.out.println("XML SCHEMA IS VALID");
                    outClient.println(input);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
