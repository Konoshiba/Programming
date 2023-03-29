package Commands;

import Exeptions.ArgsAmountException;
import Managers.ConsoleManager;

public class ExecuteScript extends Command {

    public ExecuteScript() {
        super("execute_script {file_name}", "use script from file");
    }


    @Override
    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new ArgsAmountException();
            ConsoleManager.printSuccess("Script is running '" + argument + "'...");

            return true;
        } catch (ArgsAmountException exception) {
            ConsoleManager.printErr("Usage: '" + getName() + "'");
        }
        return false;
    }
}
