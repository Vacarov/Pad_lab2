package com.lab2.client;

import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.lab2.util.JsonValidator;
import com.lab2.common.*;
import com.lab2.protocols.TCPCommunication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {

        Integer choice;
        while (true) {
            do {
                TCPCommunication tcpCommunication = new TCPCommunication();
                tcpCommunication.startConnection("127.0.0.1", 5555);

                System.out.println(" 1. GET_ALL");
                System.out.println(" 2. SORT");
                System.out.println(" select one choice");

                Scanner scan = new Scanner(System.in);
                choice = Integer.parseInt(scan.nextLine());
                switch (choice) {

                    case 1:
                        EFormatType formatType = getChosenFormatType();
                        Message message1 = new Message(ECommand.GET_ALL, formatType);
                        tcpCommunication.sendMessage(message1);
                        String response = tcpCommunication.receiveResponse();

                        if (validateJsonSchema(response)) {
                            System.out.println(response);
                        }
                        break;

                    case 2:
                        EFieldName fieldName = getChosenFieldName();
                        EOrder eOrder = getChosenOrder();
                        EFormatType eFormatType = getChosenFormatType();

                        Message message2 = new Message(ECommand.SORT, fieldName, eOrder, eFormatType);
                        tcpCommunication.sendMessage(message2);
                        String sortedResponse = tcpCommunication.receiveResponse();

                        if (validateJsonSchema(sortedResponse)) {
                            System.out.println(sortedResponse);
                        }
                        break;

                }
            } while (!choice.equals(-1));
        }
    }

    private static EOrder getChosenOrder() {
        System.out.println("Type each order: asc/desc       DEFAULT:  order=asc");
        Scanner scan = new Scanner(System.in);
        String order = scan.next();

        EOrder eOrder;
        if (order.equals(EOrder.DESCENDING.toString())) {
            eOrder = EOrder.DESCENDING;
        } else {
            eOrder = EOrder.ASCENDING;
        }

        return eOrder;
    }

    private static EFieldName getChosenFieldName() {
        System.out.println("Type by each field: name/surname/salary.    DEFAULT:  field=salary");
        Scanner scan = new Scanner(System.in);
        String field = scan.next();

        EFieldName fieldName;
        if (field.equals(EFieldName.NAME.toString())) {
            fieldName = EFieldName.NAME;
        } else if (field.equals(EFieldName.SURNAME.toString())) {
            fieldName = EFieldName.SURNAME;
        } else {
            fieldName = EFieldName.SALARY;
        }

        return fieldName;
    }

    private static EFormatType getChosenFormatType() {
        System.out.println("Type by each formatType: json/xml.    DEFAULT:  formatType=json");
        Scanner scan = new Scanner(System.in);
        String format = scan.next();

        EFormatType formatType;
        if (format.equals(EFormatType.XML.toString())) {
            formatType = EFormatType.XML;
        } else {
            formatType = EFormatType.JSON;
        }

        return formatType;
    }

    private static boolean validateJsonSchema(String response) throws IOException, ProcessingException {
        String path = "/home/vvacarov/IdeaProjects/pad_lab2/src/main/java/com/lab2/util/";

        BufferedWriter writer = new BufferedWriter(new FileWriter(path + "data/data.json"));
        writer.write(response);
        writer.close();

        File schemaFile = new File(path + "schema/schema.json");
        File dataFile = new File(path + "data/data.json");

        if (JsonValidator.isJsonValid(schemaFile, dataFile)) {
            System.out.println("Schema is Valid!");
            return true;
        } else {
            System.out.println("NOT valid schema!");
            return false;
        }
    }
}
