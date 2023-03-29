package Commands;

import Exeptions.ArgsAmountException;
import Exeptions.BadScriptException;
import Managers.CollectionManager;
import Managers.ConsoleManager;
import Managers.ScannerManager;
import SourseFiles.Ticket;
import SourseFiles.Venue;

import java.time.LocalDateTime;

public class Add extends Command {
        private final CollectionManager collectionManager;
        private final ScannerManager scannerManager;

    public Add(CollectionManager collectionManager, ScannerManager scannerManager) {
            super("add", "add a new element to colletion");
            this.collectionManager = collectionManager;
            this.scannerManager = scannerManager;
        }
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            collectionManager.addToCollection(
                    new Ticket(
                            collectionManager.generateNextId(),
                            scannerManager.askPersonName("Enter Ticket name:", "Ticket name"),
                            scannerManager.askCoordinates(),
                            LocalDateTime.now(),
                            scannerManager.askPrice(),
                            scannerManager.askRefundable(),
                            scannerManager.askTicketType(),
            new Venue(
                    (long)collectionManager.generateNextId(),
                    scannerManager.askPersonName("Enter Venue name:", "Venue name"),
                    scannerManager.askCapacity(),
                    scannerManager.askVenueType()), scannerManager.askComment())
            );
            ConsoleManager.printSuccess("Data added successfully!");
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (BadScriptException exception) {
            ConsoleManager.printErr("Bad script");
        }
        return false;
    }

}
