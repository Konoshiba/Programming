package Commands;

import Exeptions.ArgsAmountException;
import Exeptions.NullColletionEcxeption;
import Managers.CollectionManager;
import Managers.ConsoleManager;

public class Show extends Command{
    private final CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", "Show all collection elements");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();
            System.out.println(collectionManager);
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (NullColletionEcxeption exception) {
            ConsoleManager.printErr("Collection is empty!");
        }
        return false;
    }
}
