package Commands;

import Exeptions.ArgsAmountException;
import Exeptions.NullColletionEcxeption;
import Managers.CollectionManager;
import Managers.ConsoleManager;

import java.time.LocalDateTime;

public class Info extends Command {
    private final CollectionManager collectionManager;

    public Info(CollectionManager collectionManager) {
        super("info", "show info about collection");
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();
            LocalDateTime lastInitTime = collectionManager.getLastInitTime();
            String lastInitTimeString = (lastInitTime == null) ? "No commands in that session" :
                    lastInitTime.toString();

            LocalDateTime lastSaveTime = collectionManager.getLastSaveTime();
            String lastSaveTimeString = (lastSaveTime == null) ? "no saves in that session" :
                    lastSaveTime.toString();

            ConsoleManager.printInfo("Collection info::");
            ConsoleManager.printInfo(" Type: " + collectionManager.collectionType());
            ConsoleManager.printInfo(" Quantity: " + collectionManager.collectionSize());
            ConsoleManager.printInfo(" Last save: " + lastSaveTimeString);
            ConsoleManager.printInfo(" Last enter: " + lastInitTimeString);
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (NullColletionEcxeption exception) {
            ConsoleManager.printErr("Colletion is empty!");
        }
        return false;

    }
}
