package com.lab2.node;

import com.google.gson.Gson;
import com.lab2.common.Message;

import java.util.ArrayList;
import java.util.Comparator;

public class Command {
    private  Gson gson = new Gson();

    public String getAll(ArrayList<Employee> employees){
        return gson.toJson(employees);
    }

    public String getSortedEmployees(ArrayList<Employee> employees, Message message) {

        if (message.getOrder().equals("asc"))
            employees = sortEmployeesAscendingOrder(employees,message.getFieldName());
        else if(message.getOrder().equals("desc"))
            employees = sortEmployeesDescendingOrder(employees,message.getFieldName());

        return gson.toJson(employees);
    }

    private ArrayList<Employee> sortEmployeesAscendingOrder(ArrayList<Employee> employees, String sortBy) {

        switch (sortBy) {
            case "name":
                employees.sort(Comparator.comparing(Employee::getFirstName));
                break;
            case "surname":
                employees.sort(Comparator.comparing(Employee::getLastName));
                break;
            case "salary":
                employees.sort(Comparator.comparing(Employee::getSalary));
                break;
        }

        return employees;
    }

    private ArrayList<Employee> sortEmployeesDescendingOrder(ArrayList<Employee> employees, String sortBy) {

        switch (sortBy) {
            case "name":
                employees.sort(Comparator.comparing(Employee::getFirstName).reversed());
                break;
            case "surname":
                employees.sort(Comparator.comparing(Employee::getLastName).reversed());
                break;
            case "salary":
                employees.sort(Comparator.comparing(Employee::getSalary).reversed());
                break;
        }

        return employees;
    }
}
