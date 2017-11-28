package com.lab2.common;

import java.io.Serializable;

public enum EFieldName implements Serializable {
    FIRST_NAME("First Name"), LAST_NAME("Last Name"), SALARY("Salary");

    private String fieldName;

    EFieldName() {
    }

    EFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return "EFieldName{" +
                "fieldName='" + fieldName + '\'' +
                '}';
    }
}
