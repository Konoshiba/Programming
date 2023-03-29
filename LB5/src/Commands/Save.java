package Commands;

import Exeptions.ArgsAmountException;
import Managers.CollectionManager;
import Managers.ConsoleManager;

public class Save extends Command {
    private final CollectionManager collectionManager;

    public Save(CollectionManager collectionManager) {
        super("save", "save to file");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            collectionManager.saveCollection();
            ConsoleManager.printSuccess("Collection was saved!");
            return true;
        } catch (ArgsAmountException
                exception) {
            System.out.println("Usage: '" + getName() + "'");
        }
        return false;
    }

}
