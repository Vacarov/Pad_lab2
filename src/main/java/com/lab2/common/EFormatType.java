package com.lab2.common;

public enum  EFormatType {
    JSON("json"), XML("xml");

    private String formatType;

    EFormatType() {
    }

    EFormatType(String formatType) {
        this.formatType = formatType;
    }

    @Override
    public String toString() {
        return formatType;
    }
}
