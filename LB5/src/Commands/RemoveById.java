package Commands;

import Exeptions.ArgsAmountException;
import Exeptions.NullColletionEcxeption;
import Exeptions.TicketsNullException;
import Managers.CollectionManager;
import Managers.ConsoleManager;
import SourseFiles.Ticket;

public class RemoveById extends Command{
    private final CollectionManager collectionManager;

    public RemoveById(CollectionManager collectionManager) {
        super("remove_by_id {ID}", "remove element by ID");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new ArgsAmountException();
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();
            int id = Integer.parseInt(argument);
            Ticket ticket = collectionManager.getById(id);
            if (ticket == null) throw new TicketsNullException();
            collectionManager.removeById(ticket);
            ConsoleManager.printSuccess("Ticket was removed!");
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (NullColletionEcxeption exception) {
            ConsoleManager.printErr("Colletion is empty!");
        } catch (NumberFormatException exception) {
            ConsoleManager.printErr("ID must be integer!");
        } catch (TicketsNullException exception) {
            ConsoleManager.printErr("No ticket with that ID");
        }
        return false;
    }
}
