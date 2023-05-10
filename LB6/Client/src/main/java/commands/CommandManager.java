package commands;

import client.Client;
import console.*;
import data.Ticket;
import exceptions.*;
import network.CommandPacket;
import network.Common;

import java.io.IOException;
import java.nio.channels.NoConnectionPendingException;
import java.util.*;
import java.util.function.Supplier;
public class CommandManager {
    private final Map<String, Command> commands;
    private final Map<String, String> descriptions;
    private final Scanner sc;
    private final Client client;

    private final FileManager fm;

    private boolean printMode;
    private Supplier<String> valueGetter;

    private final HashSet<MyFile> fileHistory;


    public CommandManager(Scanner sc, Client client, FileManager fm) {
        this.sc = sc;
        this.commands = new HashMap<>();
        this.descriptions = new HashMap<>();
        this.client = client;
        this.fm = fm;

        this.printMode = true;
        this.valueGetter = sc::nextLine;

        this.fileHistory = new HashSet<>();
        initCommands();
    }

    public void putCommand(String name, String description, Command action){
        commands.put(name, action);
        descriptions.put(name, description);
    }

    public Command getCommand(String name) {
        return commands.get(name);
    }

    public void runCommand(String name, String arg) {
        try {
            Command command = getCommand(name);
            if (command == null) throw new CommandNotFindException("Command not find.");
            String result = getCommand(name).run(name, arg);
            if (result != null) Console.println(result, printMode);
        } catch (NullPointerException e) {
            Console.println("Command did not run successfully, problem detected.");
        }
        catch (InvalidArgumentException e) {
            if (e.getMessage() != null) Console.println(e.getMessage());
        } catch (ExecuteScriptFailedException|CommandNotFindException e) {
            Console.println(e.getMessage());
        } catch (IOException e) {
            Console.println("Error: file not found.");
        } catch (NoConnectionPendingException e) {
            Console.println("Server is not responding. Try to run command later.");
        }
    }

    public void runCommand(String name) {
        runCommand(name, null);
    }

    private Object getObjectFromServer(CommandPacket commandPacket) {
        return client.sendThenReceive(commandPacket);
    }
    
    private String getStringFromServer(CommandPacket commandPacket) {
        return (String) client.sendThenReceive(commandPacket);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here is command list:\n*");
        for (String key: descriptions.keySet()) {
            sb.append(key).append(": ").append(descriptions.get(key)).append("\n*");
        }
        return sb.substring(0,sb.length()-2);
    }

    private void setPrintMode() {
        this.printMode = true;
        this.valueGetter = sc::nextLine;
    }

    private void offPrintMode(Queue<String> q) {
        this.printMode = false;
        valueGetter = q::poll;
    }

    private int intValidator(String arg) throws InvalidArgumentException {
        try {
            return (int) new InputValidator(int.class, false, 0, Double.MAX_VALUE)
                    .validate(arg, null, true);
        }
        catch (ValidateException e) {
            Console.println(e.getMessage());
            throw new InvalidArgumentException();
        }
    }
    
    private void initCommands(){
        putCommand("help", "get information about all commands", (name, arg) -> {
            Console.println(this);
            return null;
        });
        
        putCommand("info", "get information about tickets collection (type, data, size)", (name, arg) -> {
            Console.println(getStringFromServer(new CommandPacket(name)));
            return null;
        });

        putCommand("show", "show all tickets OR argument -> {id}, show specific ticket", (name, arg) -> {
            Integer id = (arg!=null) ? intValidator(arg) : null;
            Console.println(getStringFromServer(new CommandPacket(name, id)));
            return null;
        });

        putCommand("add", "add ticket to collection", (name, arg) -> {
            int id = (int) getObjectFromServer(new CommandPacket(name, "get_new_id"));
            Ticket.setCounter(id);
            Console.println("To add ticket lead the instruction below:", printMode);
            Ticket ticket = Common.inputAndUpdateticket(false, null, printMode, valueGetter);
            return getStringFromServer(new CommandPacket(name, ticket));
        });

        putCommand("update", "argument -> {id}, update ticket by id", (name, arg) -> {
            int id = intValidator(arg);
            Object response = getObjectFromServer(new CommandPacket(name, id));
            if (response instanceof String) {
                return (String) response;
            }
            else {
                Ticket oldTicket = (Ticket) response;
                Console.println("To update ticket lead the instruction below, to save previous value type '<':", printMode);
                Ticket ticket = Common.inputAndUpdateticket(true, oldTicket, printMode, valueGetter);
                return getStringFromServer(new CommandPacket(name, ticket));
            }
        });

        putCommand("remove_by_id", "argument -> {id}, remove ticket by id", (name, arg) -> {
            int id = intValidator(arg);
            return getStringFromServer(new CommandPacket(name, id));
        });

        putCommand("clear", "clear collection", (name, arg) ->
                getStringFromServer(new CommandPacket(name)));

        putCommand("execute_script", "argument -> {file_name}, execute script file", (name, arg) -> {
            String filePath;
            try {
                filePath = (String) new InputValidator(String.class, false)
                                    .validate(arg, null, true);
            } catch (ValidateException e) {
                Console.println(e.getMessage());
                throw new InvalidArgumentException();
            }

            MyFile myFile = new MyFile(filePath);
            if (!fileHistory.add(myFile)) {
                throw new FileRecursionException("File '" + filePath + "' referring to itself.");
            }
            Queue<String> q = fm.readCommandFile(filePath);
            offPrintMode(q);

            while (q.peek() != null) {
                String[] splitLine = q.poll().trim().split(" ");

                String command = null;
                String newArg = null;
                if (splitLine.length >= 1) {
                    command = splitLine[0];
                }
                if (splitLine.length >= 2) {
                    newArg = splitLine[1];
                }
                runCommand(command, newArg);
            }

            fileHistory.remove(myFile);
            setPrintMode();
            return null;
        });

        putCommand("exit", "exit the client program", (name, arg) -> {
            Console.println("Closing client app...");
            System.exit(0);
            return null;
        });
        putCommand("show_by_less_type", "argument -> {id}, show collection all tickets if its type less than current ticket", (name, arg) -> {
            return getStringFromServer(new CommandPacket(name, arg));
        });

        putCommand("remove_greater", "argument -> {id}, remove from collection all tickets if its type greater than current ticket", (name, arg) -> {
            int id = intValidator(arg);
            return getStringFromServer(new CommandPacket(name, id));
        });

        putCommand("remove_lower", "argument -> {id}, remove from collection all tickets if its type lower than current ticket", (name, arg) -> {
            int id = intValidator(arg);
            return getStringFromServer(new CommandPacket(name, id));
        });

        putCommand("filter_less_than_price", "argument -> {price}, display all tickets where price lower than current", (name, arg) -> {
            int oscarsCount = intValidator(arg);
            return getStringFromServer(new CommandPacket(name, oscarsCount));
        });
        putCommand("remove_first", "remove first ticket", (name, arg) -> {
            return getStringFromServer(new CommandPacket(name));
        });
        putCommand("remove_last", "remove last ticket", (name, arg) -> {
            return getStringFromServer(new CommandPacket(name));
        });
        putCommand("remove_by_refundable", "argument -> {id}, remove from collection all tickets if its refundable is different", (name, arg) -> {
            boolean ref = Boolean.parseBoolean(arg);
            return getStringFromServer(new CommandPacket(name, ref));
        });

        // Server commands (only admins)
        putCommand("ADMIN:save", "save server's collection", (name, arg) -> {
            Console.println(getStringFromServer(new CommandPacket(name, "request_code")));
            String code = "000000";
            try {
                code = sc.nextLine().trim();
            } catch (NoSuchElementException e) {
                runCommand("exit");
            }
            return getStringFromServer(new CommandPacket(name, code));
        });

        putCommand("ADMIN:shutdown_server", "shut down server", (name, arg) -> {
            Console.println(getStringFromServer(new CommandPacket(name, "request_code")));
            String code = "000000";
            try {
                code = sc.nextLine().trim();
            } catch (NoSuchElementException e) {
                runCommand("exit");
            }
            return getStringFromServer(new CommandPacket(name, code));
        });
    }
}
