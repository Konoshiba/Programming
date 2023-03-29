package Managers;
import Commands.*;
import Commands.Command;
import java.util.LinkedList;
import java.util.List;
public class CommandManager {
    private final List<Command> commands = new LinkedList<>();
    private final Command add;
    private final Command clear;
    private final Command executeScriptFileName;
    private final Command exit;
    private final Command filterPrice;
    private final Command help;
    private final Command info;
    private final Command removeById;
    private final Command removeByRefurnable;
    private final Command removeFirst;
    private final Command removeLast;
    private final Command removeLower;
    private final Command save;
    private final Command showByLess;
    private final Command show;
    private final Command updateId;
    private final Command history;
    public CommandManager(Command add, Command clear, Command executeScriptFileName, Command exit, Command filterPrice, Command help,
                          Command info, Command removeById, Command removeByRefurnable, Command removeFirst, Command removeLast,
                          Command removeLower, Command save, Command showByLess, Command show, Command updateId, History history){
        this.add = add;
        this.clear = clear;
        this.executeScriptFileName = executeScriptFileName;
        this.exit = exit;
        this.filterPrice = filterPrice;
        this.help = help;
        this.info = info;
        this.removeById = removeById;
        this.removeByRefurnable = removeByRefurnable;
        this.removeFirst = removeFirst;
        this.removeLast = removeLast;
        this.removeLower = removeLower;
        this.save = save;
        this.showByLess = showByLess;
        this.show = show;
        this.updateId = updateId;
        this.history = history;
        commands.add(add);
        commands.add(clear);
        commands.add(executeScriptFileName);
        commands.add(exit);
        commands.add(filterPrice);
        commands.add(help);
        commands.add(info);
        commands.add(removeById);
        commands.add(removeByRefurnable);
        commands.add(removeFirst);
        commands.add(removeLast);
        commands.add(removeLower);
        commands.add(save);
        commands.add(showByLess);
        commands.add(show);
        commands.add(updateId);
        commands.add(removeById);
        commands.add(history);
    }
    public List<Command> getCmd(){
        return commands;
    }
    public boolean noSuchCommand(String command) {
        System.out.println("Command '" + command + "' is incorrect. Enter 'help' for help.");
        return false;
    }
    public boolean add(String argument) {
        return add.execute(argument);
    }
    public boolean clear(String argument) {
        return clear.execute(argument);
    }
    public boolean executeScriptFileName(String argument) {return executeScriptFileName.execute(argument);}
    public boolean exit(String argument) {
        return exit.execute(argument);
    }
    public boolean filterPrice(String argument) {
        return filterPrice.execute(argument);
    }
    public boolean help(String argument) {
        String str = "";
        for(Command command: commands){
            str+=command.getName() + ": " + command.getDisc() + "\n";
        }

        return help.execute(str);
    }
    public boolean info(String argument) {
        return info.execute(argument);
    }
    public boolean removeById(String argument) {return removeById.execute(argument);}
    public boolean removeByRefundable(String argument) {
        return removeByRefurnable.execute(argument);
    }
    public boolean removeFirst(String argument) {return removeFirst.execute(argument);}
    public boolean removeLast(String argument) {
        return removeLast.execute(argument);
    }
    public boolean removelower(String argument) {
        return removeLower.execute(argument);
    }
    public boolean save(String argument) {
        return save.execute(argument);
    }
    public boolean showByLess(String argument) {return showByLess.execute(argument);}
    public boolean show(String argument) {
        return show.execute(argument);
    }
    public boolean updateId(String argument) {
        return updateId.execute(argument);
    }
    public boolean history(String argument) {
        return history.execute(argument);
    }
}