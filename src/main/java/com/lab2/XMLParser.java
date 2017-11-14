package com.lab2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

public class XMLParser {

    public static void getInformationalNodeConfig(File xmlFile) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        InformationalNode informationalNode = null;

        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("node");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                org.w3c.dom.Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    int id = Integer.parseInt(eElement.getAttribute("id"));
                    String ip = eElement.getElementsByTagName("ip").item(0).getTextContent();
                    int port = Integer.parseInt(eElement.getElementsByTagName("tcpPort").item(0).getTextContent());
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }

    public static ArrayList<com.lab2.Node> getNodeList(File xmlFile) {

        ArrayList<com.lab2.Node> nodes = new ArrayList<>();
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
                    nodes.add(new com.lab2.Node(new InetSocketAddress(ip, port)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodes;
    }
}
