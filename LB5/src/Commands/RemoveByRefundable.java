package Commands;

import Exeptions.ArgsAmountException;
import Exeptions.NullColletionEcxeption;
import Managers.CollectionManager;
import Managers.ConsoleManager;
import Managers.ScannerManager;

public class RemoveByRefundable extends Command {
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public RemoveByRefundable(CollectionManager collectionManager, ScannerManager scannerManager) {
        super("remove_by_refundable", "remove element by refundable");
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    @Override
    public boolean execute(String arg) {
        try {
            if (arg.isEmpty()) throw new ArgsAmountException();
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();
            boolean rebundable = Integer.parseInt(arg) == 1;
            collectionManager.removeByRefundable(rebundable);
            ConsoleManager.printSuccess("Tickets was removed");
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        } catch (NullColletionEcxeption exception) {
            ConsoleManager.printErr("Collection is  NULL!");
        } catch (NumberFormatException exception) {
            ConsoleManager.printErr("ID must be an integer!");
        }
        return false;
    }
}
