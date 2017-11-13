package com.pad.lab2;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import static org.apache.commons.lang3.SerializationUtils.serialize;

public class ClientConnection extends Thread {

    private Socket clientSocket;

    public ClientConnection(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        Employee[] s = new Employee[getEmployees().size()];
        try {
            serialize((Employee[]) getEmployees().toArray(s), clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Employee> getEmployees() {
        return new ArrayList<Employee>() {{
            add(new Employee("Laur", "Balaur",  501.0));
            add(new Employee("Fat", "Frumos",  502.0));
            add(new Employee("Ileana", "Consinzeana",  503.0));
            add(new Employee("Danila", "Prepeleac",  304.0));
            add(new Employee("Ivan", "Turbinca", 505.0));
        }};
    }
}
