package Commands;

import Exeptions.ArgsAmountException;
import Managers.ConsoleManager;

public class Exit extends Command {

    public Exit() {
        super("exit", "finish program without saving");
    }

    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new ArgsAmountException();
            ConsoleManager.printSuccess("Good Bye");
            System.exit(0);
            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        }
        return false;
    }
}
