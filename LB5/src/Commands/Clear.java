package Commands;

import Exeptions.ArgsAmountException;
import Managers.CollectionManager;
import Managers.ConsoleManager;

public class Clear extends Command{
    private final CollectionManager collectionManager;
    public Clear(CollectionManager collectionManager) {
        super("clear", "clear collection");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            collectionManager.clearCollection();
            ConsoleManager.printSuccess("Collection is cleared!");
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        }
        return false;
    }
}

