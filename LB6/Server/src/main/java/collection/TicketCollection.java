package collection;
import console.FileManager;
import console.ReadDoomParser;
import data.*;
import exceptions.ExecuteScriptFailedException;
import exceptions.InitialFileInvalidValuesException;
import exceptions.InvalidArgumentException;
import network.Common;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicketCollection {
    private ArrayList<Ticket> al;
    private final LocalDateTime initDate;
    private final Logger log = LoggerFactory.getLogger(TicketCollection.class);

    private String startFilePath;
    private ReadDoomParser dp;

    public TicketCollection(FileManager fm) {
        this(fm, System.getenv("MYPROJECTFILE"));
    }
    
    public TicketCollection(FileManager fm, String startFilePath) {
        this.al = new ArrayList<>();
        this.initDate = LocalDateTime.now();
        this.startFilePath = null;
        loadXMLFile(fm, startFilePath);
    }

    private void loadXMLFile(FileManager fm, String filePath) {
        try {
            al = dp.readFile(filePath);
            HashSet<Integer> ids = new HashSet<>();
            for (Ticket t: al) {
                if (!ids.add(t.getId()))
                    throw new InitialFileInvalidValuesException(
                            "ticket's ids in xml file must have unique values.");
            }

            al.forEach(ticket -> {
                try {
                    Common.inputAndUpdateticket(true, ticket, false, () -> "#validate_initial");
                } catch (ExecuteScriptFailedException e) {
                    if (e.getMessage() != null) log.warn(e.getMessage());
                    al = new ArrayList<>();
                    throw new InitialFileInvalidValuesException();
                }
            });

            this.startFilePath = (filePath != null) ? filePath : FileManager.DEFAULT_START_FILE;
            if (filePath == null) log.info("using default file '{}' to save collection", FileManager.DEFAULT_START_FILE);
            else log.info("collection with {} tickets was loaded from file '{}'", al.size(), startFilePath);
        }
        catch (InitialFileInvalidValuesException e) {
            if (e.getMessage() != null) {
                log.error(e.getMessage());
            } else {
                log.error("xml file not loaded.");
            }
            System.exit(0);
        }
    }

    public int size() {
        return al.size();
    }

    public ArrayList<Ticket> getAl() {
        return al;
    }

    public String getStartFilePath() {
        return startFilePath;
    }
    public Ticket getTicketById(int id) throws InvalidArgumentException {
        return al.stream()
                .filter(ticket -> ticket.getId() == id)
                .findFirst()
                .orElseThrow(() -> new InvalidArgumentException("Ticket with id " + id + " not found."));
    }

    public int getMaxId() {
        return al.stream()
                .max(Comparator.comparing(Ticket::getId))
                .orElse(new Ticket())
                .getId();
    }

    public Ticket getLowestByType() {
        return al.stream()
                .min(Comparator.comparing(Ticket::getType))
                .orElse(null);
    }

    public void addticket(Ticket t){
        al.add(t);
    }

    public boolean removeticketById(int id) {
        int oldSize = al.size();
        al = al.stream()
                .filter(ticket -> ticket.getId() != id)
                .collect(Collectors.toCollection(ArrayList<Ticket>::new));
        return oldSize != al.size();
    }

    public boolean removeGreater(int id) throws InvalidArgumentException {
        Ticket m = getTicketById(id);
        int oldSize = al.size();
        al = al.stream()
                .filter(ticket -> ticket.compareTo(m) <= 0)
                .collect(Collectors.toCollection(ArrayList<Ticket>::new));
        return oldSize != al.size();
    }

    public void removeFirst() throws InvalidArgumentException {
        al.remove(0);
    }
    public void removeLast() throws InvalidArgumentException {
        al.remove(al.size()-1);
    }

    public boolean removeLower(int id) throws InvalidArgumentException {
        Ticket m = getTicketById(id);
        int oldSize = al.size();
        al = al.stream()
                .filter(ticket -> ticket.compareTo(m) >= 0)
                .collect(Collectors.toCollection(ArrayList<Ticket>::new));
        return oldSize != al.size();
    }
    public String show_by_less_type (TicketType arg){
        String t = "ID NAME";
        for (Ticket ticket : al) {
            if (ticket.getType().ordinal() > arg.ordinal()) {
                t += ticket.toString() + "\n";
            }
        }
        return t.substring(0, t.length()-1);
    }
    public boolean remove_by_refundable(boolean arg) throws InvalidArgumentException {
        int oldSize = al.size();
        al = al.stream()
                .filter(ticket -> ticket.getRefundable() != arg)
                .collect(Collectors.toCollection(ArrayList<Ticket>::new));
        return oldSize != al.size();

    }

    public void clear() {
        al.clear();
    }

    @Override
    public String toString() {
        if (size() == 0) return "Collection is empty.";
        StringBuilder sb = new StringBuilder("tickets:\n");
        sb.append("ID NAME\n");
        al.forEach(ticket -> sb.append(ticket).append('\n'));
        return sb.toString();
    }

    public String getInfo() {
        return "*Collection type: " + al.getClass().getName() +
                "\n*Initialization date: " + initDate.toString() +
                "\n*Total elements: " + al.size();
    }
}
