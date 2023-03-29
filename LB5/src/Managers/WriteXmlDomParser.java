package Managers;
import SourseFiles.Ticket;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class WriteXmlDomParser {

    private static String FILENAME;

    private static Element serializeTicket(Ticket ticket, Document document){
        Element root = document.createElement("ticket");

        Element id = document.createElement("id");
        root.appendChild(id);
        id.setTextContent(String.valueOf(ticket.getId()));

        Element name = document.createElement("name");
        root.appendChild(name);
        name.setTextContent(ticket.getName());

        Element creationDate = document.createElement("creationDate");
        root.appendChild(creationDate);
        creationDate.setTextContent(ticket.getCreationDate().toString());

        Element type = document.createElement("type");
        root.appendChild(type);
        type.setTextContent(ticket.getType().name());

        Element coordinates = document.createElement("coordinates");
        root.appendChild(coordinates);

        Element coordinateX = document.createElement("coordinateX");
        coordinates.appendChild(coordinateX);
        coordinateX.setTextContent(Float.toString(ticket.getCoordinates().getX()));

        Element coordinateY = document.createElement("coordinateY");
        coordinates.appendChild(coordinateY);
        coordinateY.setTextContent(Integer.toString(ticket.getCoordinates().getY()));

        Element refundable = document.createElement("refundable");
        root.appendChild(refundable);
        refundable.setTextContent(ticket.getRefundable().toString());
        Element venue = document.createElement("venue");
        root.appendChild(venue);

        Element idV = document.createElement("id");
        venue.appendChild(idV);
        idV.setTextContent(Long.toString(ticket.getVenue().getId()));

        Element nameV = document.createElement("name");
        venue.appendChild(nameV);
        nameV.setTextContent(ticket.getVenue().getName());

        Element capacity = document.createElement("capacity");
        venue.appendChild(capacity);
        capacity.setTextContent(Long.toString(ticket.getVenue().getCapacity()));

        Element typeV = document.createElement("type");
        venue.appendChild(typeV);
        typeV.setTextContent(ticket.getVenue().getType().name());

        Element price = document.createElement("price");
        root.appendChild(price);
        price.setTextContent(Float.toString(ticket.getPrice()));
        Element comment = document.createElement("comment");
        root.appendChild(comment);
        comment.setTextContent(ticket.getComment());
        return root;
    }

    public static void write(List<Ticket> list, String FILENAME) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.newDocument();

            Element tickets = doc.createElement("tickets");
            doc.appendChild(tickets);

            for(Ticket ticket: list){
                tickets.appendChild(serializeTicket(ticket, doc));
            }

            DOMSource dom = new DOMSource(doc);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();

            StreamResult streamResult = new StreamResult(new File(FILENAME));
            transformer.transform(dom, streamResult);
        } catch (ParserConfigurationException | TransformerException e) {
            System.out.println("Unexpected exception ");
            e.printStackTrace();
        }
    }
}
