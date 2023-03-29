package Managers;

import SourseFiles.*;
import org.w3c.dom.Node;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class ParseXML {
    private static Coordinates getCoordinatesFrom(Node node) {
        Coordinates res = new Coordinates();

        for(int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node cur = node.getChildNodes().item(i);

            switch (cur.getNodeName()) {
                case "coordinateX":
                    res.setX(Float.parseFloat(cur.getTextContent()));
                    break;
                case "coordinateY":
                    res.setY(Integer.parseInt(cur.getTextContent()));
                    break;
            }
        }
        return res;
    }

    private static Venue getVenueFrom(Node node) {
        Venue venue = new Venue((long)0, "ERROR", 0, VenueType.BAR);
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node cur = node.getChildNodes().item(i);
            switch (cur.getNodeName()) {
                case "id":
                    venue.setId(Long.parseLong(cur.getTextContent()));
                    break;
                case "name":
                    venue.setName(cur.getTextContent());
                    break;
                case "capacity":
                    venue.setCapacity(Integer.parseInt(cur.getTextContent()));
                    break;
                case "type":
                    venue.setType(VenueType.valueOf(cur.getTextContent()));
                    break;
            }

        }
        return venue;
    }

    private static Ticket getTicketFrom(Node node) {
        Coordinates coord = new Coordinates();
        coord.setY(0);
        coord.setX(0);

        Ticket ticket = new Ticket(0, "ERROR", coord, LocalDateTime.now(), 20, true,
                TicketType.VIP, new Venue((long) 10, "Koo", 30, VenueType.BAR), "");
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            Node cur = node.getChildNodes().item(i);
            switch (cur.getNodeName()) {
                case "id":
                    ticket.setId(Integer.parseInt(cur.getTextContent()));
                    break;
                case "name":
                    ticket.setName(cur.getTextContent());
                    break;
                case "coordinates":
                    ticket.setCoordinates(getCoordinatesFrom(cur));
                    break;
                case "creationDate":
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    ticket.setCreationDate(LocalDateTime.parse(cur.getTextContent()));
                    break;
                case "price":
                    ticket.setPrice(Float.parseFloat(cur.getTextContent()));
                    break;
                case "refundable":
                    ticket.setRefundable(Boolean.parseBoolean(cur.getTextContent()));
                case "type":
                    if(cur.getTextContent().contains("BUDGETARY")){
                        ticket.setType(TicketType.BUDGETARY);
                    }
                    if(cur.getTextContent().contains("VIP")){
                        ticket.setType(TicketType.VIP);
                    }
                    if(cur.getTextContent().contains("USUAL")){
                        ticket.setType(TicketType.USUAL);
                    }
                    break;
                case "venue":
                    ticket.setVenue(getVenueFrom(cur));
                    break;
                case "comment":
                    ticket.setComment(cur.getTextContent());
            }
        }
        return ticket;
    }

    public static ArrayList<Ticket> readFile(String filename) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true); //для отображения null как 0
            DocumentBuilder db = dbf.newDocumentBuilder();


            Document doc = db.parse(new File(filename));

            doc.getDocumentElement().normalize();

            NodeList templist = doc.getElementsByTagName("tickets");
            NodeList list = templist.item(0).getChildNodes();

            ArrayList<Ticket> ticketCollection = new ArrayList<>();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if(node.getNodeName()!="#text")
                    try{
                ticketCollection.add(getTicketFrom(node));}
                catch(NumberFormatException e){
                        ConsoleManager.printErr("Ticket corrupted");
                }
            }
            return ticketCollection;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Unexpected error");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
