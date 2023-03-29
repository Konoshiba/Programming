package Commands;

import Exeptions.NullColletionEcxeption;
import Managers.CollectionManager;
import Managers.ConsoleManager;
import Managers.ScannerManager;

public class FilterPrice extends Command{
    private final CollectionManager collectionManager;
    public FilterPrice(CollectionManager collectionManager, ScannerManager scannerManager) {
        super("filter_price", "Show array of prices");
        this.collectionManager = collectionManager;
    }
    @Override
    public boolean execute(String argument) {
        try {
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();
            collectionManager.FilterPrice();
            return true;
        } catch (NullColletionEcxeption exception) {
            ConsoleManager.printErr("Collection is  NULL!");
        }
        return false;
    }
}
