package com.lab2;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 1L;
    private String type;
    private String text;

    public Message(String type, String text) {
        this.type = type;
        this.text = text;
    }

    public Message() {
    }
}
