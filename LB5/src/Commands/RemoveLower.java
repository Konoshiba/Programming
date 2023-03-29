package Commands;

import Exeptions.ArgsAmountException;
import Exeptions.NullColletionEcxeption;
import Exeptions.TicketsNullException;
import Managers.CollectionManager;
import Managers.ConsoleManager;
import Managers.ScannerManager;
import SourseFiles.Ticket;

public class RemoveLower extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;
    public RemoveLower(CollectionManager collectionManager, ScannerManager scannerManager) {
        super("remove_lower {ID}", "remove elements lower than given");
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (arg.isEmpty()) throw new ArgsAmountException();
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();
            int id = Integer.parseInt(arg);
            Ticket ticket = collectionManager.getById(id);
            if (ticket == null) throw new TicketsNullException();
            collectionManager.removeLower(ticket);
            ConsoleManager.printSuccess("Elements removed!");
            return true;

        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (NullColletionEcxeption exception) {
        ConsoleManager.printErr("Collection is  NULL!");
    } catch (NumberFormatException exception) {
        ConsoleManager.printErr("ID must be an integer!");
    } catch (
    TicketsNullException exception) {
        ConsoleManager.printErr("No such ticket with that ID");
    }
        return false;
    }
}
