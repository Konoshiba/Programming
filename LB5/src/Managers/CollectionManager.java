package Managers;

import Exeptions.WrongNameException;
import SourseFiles.Ticket;
import SourseFiles.TicketType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class CollectionManager {
    private ArrayList<Ticket> TicketCollection = new ArrayList<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private FileManager fileManager;
    public CollectionManager(FileManager fileManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.fileManager = fileManager;

        TicketCollection = fileManager.readCollection();
        ArrayList<Integer> temp = new ArrayList<>();
        for (Ticket ticket : TicketCollection) {
            temp.add(ticket.getId());
        }
        try {
            for (int i = 0; i < temp.size(); i++) {
                int t1 = temp.get(i);
                for (int j = 0; j < temp.size(); j++) {
                    if (i == j) continue;
                    int t2 = temp.get(j);
                    if (t1 == t2) {
                        TicketCollection.remove(j);
                        throw new WrongNameException();
                    }
                    }
                }

            }
        catch(WrongNameException e){
            ConsoleManager.printErr("ID has a duplicate");
        }
    }
    public ArrayList<Ticket> getCollection() {
        return TicketCollection;
    }


    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }


    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }


    public String collectionType() {
        return TicketCollection.getClass().getName();
    }
    public int collectionSize() {
        return TicketCollection.size();
    }


    public Ticket getLast() {
        if (TicketCollection.isEmpty()) return null;
        return TicketCollection.get(TicketCollection.size()-1);
    }
    public void removeLower(Ticket arg) {
        ArrayList<Ticket> tmp = new ArrayList<>();
        for (Ticket ticket : TicketCollection) {
            if (ticket.compareTo(arg) < 0) {
                tmp.add(ticket);
            }
        }
        for(int i = 0; i < tmp.size(); i++){
            TicketCollection.remove(tmp.get(i));
        }
    }
    public void removeByRefurnable(Ticket arg){
        Ticket id = null;
        for (Ticket ticket : TicketCollection){
            if(ticket.getRefundable().equals(arg.getRefundable())){
                id = ticket;
                break;
            }
        }
        TicketCollection.remove(id);
    }
    public void ShowByLessType(TicketType arg) {
        for (Ticket ticket : TicketCollection) {
            if (ticket.getType().ordinal() > arg.ordinal()) {
                System.out.println(ticket.toString());
            }
        }
    }
    public void removeByRefundable(boolean arg){
        ArrayList<Ticket> tmp = new ArrayList<>();
        for(Ticket ticket: TicketCollection){
            if(ticket.getRefundable().equals(arg)){
                tmp.add(ticket);
            }
        }
        for(Ticket ticket: tmp){
            TicketCollection.remove(ticket);
        }
    }
    public void FilterPrice(){
        float max = 0;
        ArrayList<Float> Elements = new ArrayList<>();
        for(Ticket ticket: TicketCollection){
            Elements.add(ticket.getPrice());
        }
        Collections.sort(Elements);
        System.out.println(Elements);
    }

    public Ticket getById(int id) {
        for (Ticket ticket : TicketCollection) {
            if (ticket.getId().equals(id)) return ticket;
        }
        return null;
    }
    public void removeById(Ticket person) {
        TicketCollection.remove(person);
    }
    public void removeFirst(){TicketCollection.remove(0);}
    public void removeLast(){TicketCollection.remove(TicketCollection.size()-1);}

    public void addToCollection(Ticket person){
        ArrayList<Ticket> tmp = new ArrayList<>();
        while (!TicketCollection.isEmpty() && TicketCollection.get(0).compareTo(person) < 0) {
            tmp.add(TicketCollection.get(0));
            TicketCollection.remove(0);

        }
        TicketCollection.add(0, person);
        while (!tmp.isEmpty()) {
            TicketCollection.add(0, tmp.get(tmp.size() - 1));
            tmp.remove(tmp.size()-1);
        }

    }

    public void addFirst(Ticket person){
        TicketCollection.add(0, person);
    }

    public void clearCollection() {
        TicketCollection.clear();
    }
    public Integer generateNextId() {
        int max = 0;
        if (TicketCollection.isEmpty()) return 1;
        for(int i = 0; i < TicketCollection.size(); i++){
            if (TicketCollection.get(TicketCollection.size()-i - 1).getId() > max){
                max = TicketCollection.get(TicketCollection.size()-i - 1).getId();
            }
        }
        return max + 1;
    }

    public void saveCollection() {
        fileManager.writeCollection(TicketCollection);
        lastSaveTime = LocalDateTime.now();
    }
    private void loadCollection() {
        TicketCollection = fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }
    public void removeFromCollection(Ticket ticketToRemove, Ticket newTicket) {
        int i = 0;
        if (!ticketToRemove.equals(newTicket)){
            TicketCollection.remove(ticketToRemove);
        }
    }
    @Override
    public String toString() {
        if (TicketCollection.isEmpty()) return "Collection is empty!";
        StringBuilder info = new StringBuilder();
        for (Ticket ticket : TicketCollection) {
            info.append(ticket.toString());
            if (ticket != TicketCollection.get(TicketCollection.size()-1)) {
                info.append("\n\n");
            }
        }
        return info.toString();
    }
}
