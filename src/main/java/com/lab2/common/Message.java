package com.lab2.common;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String command;
    private String text;

    public Message() {
    }

    public Message(String type, String text) {
        this.command = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "command='" + command + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
