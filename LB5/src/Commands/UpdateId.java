package Commands;

import Exeptions.ArgsAmountException;
import Exeptions.BadScriptException;
import Exeptions.NullColletionEcxeption;
import Exeptions.TicketsNullException;
import Managers.CollectionManager;
import Managers.ConsoleManager;
import Managers.ScannerManager;
import SourseFiles.*;

import java.time.LocalDateTime;

public class UpdateId extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public UpdateId(CollectionManager collectionManager, ScannerManager scannerManager) {
        super("update {ID}", "update element by ID");
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new ArgsAmountException();
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();

            int id = Integer.parseInt(argument);
            Ticket ticket = collectionManager.getById(id);
            Venue venue = ticket.getVenue();
            if (ticket == null) throw new TicketsNullException();
            String name = ticket.getName();
            Coordinates coordinates = ticket.getCoordinates();
            LocalDateTime creationDate = ticket.getCreationDate();
            float price = ticket.getPrice();
            Boolean refundable = ticket.getRefundable();
            TicketType type = ticket.getType();
            String venueName = venue.getName();
            int capacity = venue.getCapacity();
            VenueType venueType = venue.getType();
            String comment = ticket.getComment();


            if (scannerManager.askQuestion("Change Ticket name?"))
                name = scannerManager.askPersonName("Enter Ticket name:", "Ticket name");
            if (scannerManager.askQuestion("Change Ticket coordinates?"))
                coordinates = scannerManager.askCoordinates();
            if (scannerManager.askQuestion("Change Ticket price?"))
                price = scannerManager.askPrice();
            if (scannerManager.askQuestion("Change Ticket type?"))
                type = scannerManager.askTicketType();
            if (scannerManager.askQuestion("Change Ticket refundable?"))
            {
                refundable = scannerManager.askRefundable();
            }
            if (scannerManager.askQuestion("Change Venue?"))
            {
                if (scannerManager.askQuestion("Change Venue name?")){
                    venueName = scannerManager.askPersonName("Enter Venue name:", "Venue name");
                }
                if (scannerManager.askQuestion("Change Venue capacity?")){
                    capacity = scannerManager.askCapacity();
                }
                if (scannerManager.askQuestion("Change Venue Type?")) {
                    venueType = scannerManager.askVenueType();
                }
            }
            if (scannerManager.askQuestion("Change Comment?")){
                comment = scannerManager.askComment();
            }

            Ticket newTicket = new Ticket(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    price,
                    refundable,
                    type,
                    new Venue((long) id, venueName, capacity, venueType), comment);
            collectionManager.addToCollection(newTicket);
            collectionManager.removeFromCollection(ticket, newTicket);
            ConsoleManager.printSuccess("Info was successfully updated");
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (NullColletionEcxeption exception) {
            ConsoleManager.printErr("Collection is  NULL!");
        } catch (NumberFormatException exception) {
            ConsoleManager.printErr("ID must be an integer!");
        } catch (TicketsNullException exception) {
            ConsoleManager.printErr("No such ticket with that ID");
        } catch (BadScriptException exception) {
            ConsoleManager.printErr("Script is bad.");
        }
        return false;
    }
}
