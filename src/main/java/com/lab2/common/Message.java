package com.lab2.common;

import java.io.Serializable;

public class Message implements Serializable{
    private static final long serialVersionUID = 1L;
    private String command;
    private String fieldName;
    private String text;

    public Message() {
    }

    public Message(String command) {
        this.command = command;
    }

    public Message(String command, String fieldName) {
        this.command = command;
        this.fieldName = fieldName;
    }

    public Message(String command, String fieldName, String text) {
        this.command = command;
        this.fieldName = fieldName;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Message{" +
                "command=" + command +
                ", fieldName=" + fieldName +
                ", text='" + text + '\'' +
                '}';
    }
}
