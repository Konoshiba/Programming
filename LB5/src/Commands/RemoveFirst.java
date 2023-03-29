package Commands;

import Exeptions.ArgsAmountException;
import Exeptions.NullColletionEcxeption;
import Managers.CollectionManager;
import Managers.ConsoleManager;

public class RemoveFirst extends Command{
    private final CollectionManager collectionManager;

    public RemoveFirst(CollectionManager collectionManager) {
        super("remove_first", "remove first element");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();
            collectionManager.removeFirst();
            ConsoleManager.printSuccess("First ticket was removed!");
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (NullColletionEcxeption exception) {
            ConsoleManager.printErr("Colletion is empty!");
        }
        return false;
    }
}
