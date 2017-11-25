package com.lab2.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
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
}
