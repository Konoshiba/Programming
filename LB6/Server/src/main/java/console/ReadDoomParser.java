package console;
import data.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;


public class ReadDoomParser {
    private static Coordinates getCoordinatesFrom(Node node) {
        Coordinates res = new Coordinates(0f, 0);

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
        Venue venue = new Venue( "ERROR", 0, VenueType.BAR);
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
        Coordinates coord = new Coordinates(0f, 0);
        coord.setY(0);
        coord.setX(0);

        Ticket ticket = new Ticket("ERROR", coord, 20, true,
                TicketType.VIP, new Venue("ERROR", 30, VenueType.BAR), "");
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
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MMMM.yyyy", Locale.US);
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
                        System.out.println("Ticket corrupted");
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
