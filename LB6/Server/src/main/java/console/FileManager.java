package console;

import collection.TicketCollection;
import data.Ticket;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class FileManager {
    public static final String DEFAULT_START_FILE = "db.xml";

    public void writeXMLFile(String path, TicketCollection tc) throws FileNotFoundException {
        WriteXmlDomParser.write(tc.getAl(), path);
    }
}
