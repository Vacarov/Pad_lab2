package com.lab2.client;

import com.google.gson.Gson;
import com.lab2.common.Message;

import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private PrintWriter out;

    public void startConnection(String ip, int port) throws Exception {
        clientSocket = new Socket(ip, port);
        System.out.println("This is client  with port: " + clientSocket.getLocalPort());
        out = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void sendMessage(Message message) throws Exception {
        Gson gson = new Gson();
//        MessageTransformer messageTransformer = new MessageTransformer();
//        final String json = messageTransformer.transformIntoGson(message);
        out.println(gson.toJson(message));
    }
}
