package com.lab2.util;

import com.lab2.common.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class XMLParser {

    public static ArrayList<com.lab2.node.Node> getNodeList(File xmlFile) {

        ArrayList<com.lab2.node.Node> nodes = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            NodeList nodeList = doc.getElementsByTagName("node");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {

                Node nNode = nodeList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    String ip = eElement.getElementsByTagName("ip").item(0).getTextContent();
                    int port = Integer.parseInt(eElement.getElementsByTagName("port").item(0).getTextContent());
                    nodes.add(new com.lab2.node.Node(new InetSocketAddress(ip, port)));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nodes;
    }

    public static void writeEmployeesInXmlFile(ArrayList<Employee> employees) {
        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();

            Element root = doc.createElement("employees");
            doc.appendChild(root);

            for (Employee employee : employees) {
                Element Details = doc.createElement("employee");
                root.appendChild(Details);

                Element name = doc.createElement("name");
                name.appendChild(doc.createTextNode(String.valueOf(employee
                        .getName())));
                Details.appendChild(name);

                Element id = doc.createElement("surname");
                id.appendChild(doc.createTextNode(String.valueOf(employee.getSurname())));
                Details.appendChild(id);

                Element mmi = doc.createElement("salary");
                mmi.appendChild(doc.createTextNode(String.valueOf(employee.getSalary())));
                Details.appendChild(mmi);
            }

            // Save the document to the disk file
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();

            // format the XML nicely
            aTransformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            aTransformer.setOutputProperty(
                    "{http://xml.apache.org/xslt}indent-amount", "4");
            aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            try {
                BufferedWriter fos = new BufferedWriter(new FileWriter("/home/vvacarov/IdeaProjects/pad_lab2/src/main/java/com/lab2/util/data/data.xml"));
                StreamResult result = new StreamResult(fos);
                aTransformer.transform(source, result);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (TransformerException ex) {
            System.out.println("Error outputting document");

        } catch (ParserConfigurationException ex) {
            System.out.println("Error building document");
        }
    }
}
