package Commands;

import Managers.Chronicler;
import Managers.ConsoleManager;

public class History extends Command {
    private final Chronicler chronicler;

    public History(Chronicler chronicler) {
        super("history", "View last 12 commands");
        this.chronicler = chronicler;
    }

    @Override
    public boolean execute(String argument) {
        String[] history = chronicler.getHistory();
        System.out.println("Last 12 commands:");
        for (String s : history) {
            if (!(s == null)) {
                ConsoleManager.printSuccess(s);
            }
        }

        return true;
    }
}
