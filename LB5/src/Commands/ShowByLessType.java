package Commands;

import Exeptions.ArgsAmountException;
import Exeptions.NullColletionEcxeption;
import Managers.CollectionManager;
import Managers.ConsoleManager;
import Managers.ScannerManager;
import SourseFiles.TicketType;

public class ShowByLessType extends Command{
    private final CollectionManager collectionManager;
    private final ScannerManager scannerManager;

    public ShowByLessType(CollectionManager collectionManager, ScannerManager scannerManager) {
        super("show_by_less_type", "show tickets tickets with the less type");
        this.collectionManager = collectionManager;
        this.scannerManager = scannerManager;
    }

    @Override
    public boolean execute(String arg) {
        try{
            if (arg.isEmpty()) throw new ArgsAmountException();
            if (collectionManager.collectionSize() == 0) throw new NullColletionEcxeption();
            TicketType type = TicketType.valueOf(arg);
            collectionManager.ShowByLessType(type);
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        }catch (NullColletionEcxeption exception) {
            ConsoleManager.printErr("Collection is  NULL!");
        } catch (NumberFormatException exception) {
            ConsoleManager.printErr("ID must be an integer!");
        }
        return false;
    }
}
