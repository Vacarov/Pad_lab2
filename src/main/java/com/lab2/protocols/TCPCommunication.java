package com.lab2.protocols;

import com.google.gson.Gson;
import com.lab2.common.Message;

import java.io.*;
import java.net.Socket;

public class TCPCommunication {

    private Socket clientSocket;
    private PrintWriter out;

    public void startConnection(String ip, int port) throws Exception {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void receiveResponse() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String input;
        while ((input = in.readLine()) != null) {
            System.out.println(input);
        }
    }

    public Message receiveMessage(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Gson gson = new Gson();
        return gson.fromJson(in.readLine(), Message.class);
    }

    public String receiveMessageInJson(Socket clientSocket) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return in.readLine();
    }

    public void sendMessage(Message message) throws IOException {
        Gson gson = new Gson();
        final String json = gson.toJson(message);
        out.println(json);
    }
}
