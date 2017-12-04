package com.lab2.common;

import java.io.Serializable;

public class Message implements Serializable{
    private static final long serialVersionUID = 1L;
    private String command;
    private String fieldName;
    private String text;
    private String order;

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

    public Message(String command, String fieldName, String text, String operation) {
        this.command = command;
        this.fieldName = fieldName;
        this.text = text;
        this.order = operation;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Message{" +
                "command='" + command + '\'' +
                ", fieldName='" + fieldName + '\'' +
                ", text='" + text + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
