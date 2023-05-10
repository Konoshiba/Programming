package commands;

import collection.TicketCollection;
import console.FileManager;
import data.Ticket;
import data.TicketType;
import exceptions.CommandNotFindException;
import exceptions.ExecuteScriptFailedException;
import exceptions.InvalidArgumentException;
import network.CommandPacket;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandManager {
    private final Map<String, Command> commands;
    private final TicketCollection tc;
    private final FileManager fm;
    private final Logger log = LoggerFactory.getLogger(TicketCollection.class);
    private String serverCode = "000000";

    public CommandManager(TicketCollection tc, FileManager fm) {
        this.commands = new HashMap<>();
        this.tc = tc;
        this.fm = fm;

        initCommands();
    }
    
    public void putCommand(String name, Command action){
        commands.put(name, action);
    }
    
    public Command getCommand(String name) {
        return commands.get(name);
    }
    
    public Object runCommand(CommandPacket cp) {
        try {
            Command command = getCommand(cp.getName());
            if (command == null) {
                log.debug("processing command '{}': command not found.", cp.getName());
                throw new CommandNotFindException("Command not found.");
            }
            return command.run(cp.getArg());
        } catch (NullPointerException e) {
            log.debug("processing command '{}': command did not run successfully, problem detected.", cp.getName());
            return "Command did not run successfully, problem detected.";
        }
        catch (InvalidArgumentException e) {
            if (e.getMessage() != null) return e.getMessage();
        } catch (ExecuteScriptFailedException|CommandNotFindException e) {
            return e.getMessage();
        } catch (IOException e) {
            log.debug("processing command '{}': file not found.", cp.getName());
            return "Error: file not found";
        }
        return null;
    }

    private void initCommands(){
        putCommand("info", (argObject) -> tc.getInfo());

        putCommand("show", (argObject) -> {
            if (argObject == null) return tc.toString();
            else {
                int id = (int) argObject;
                Ticket t = tc.getTicketById(id);

                final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MMMM.yyyy", Locale.US);
               return String.format("Id: %d\nName: %s\nCoordinates:\n\tx: %f\n\ty: %d\nCreation Date: %s\nPrice: %f\nTicketType: %s\nRefundable: %s\n" +
                                "Venue:\n\tId: %d\n\tName: %s\n\tCapacity: %d\n\tType: %s\n",
                        t.getId(), t.getName(), t.getCoordinates().getX(), t.getCoordinates().getY(),
                        t.getCreationDate().format(dtf), t.getPrice(), t.getType(), t.getRefundable(), t.getVenue().getId(), t.getVenue().getName(),
                        t.getVenue().getCapacity(), t.getVenue().getType());
            }
        });

        putCommand("add", (argObject) -> {
            if (argObject instanceof String) {
                return tc.getMaxId();
            } else {
                Ticket ticket = (Ticket) argObject;
                tc.addticket(ticket);
                return "Successfully added element!";
            }
        });

        putCommand("update", (argObject) -> {
            if (argObject instanceof Integer) {
                int id = (int) argObject;
                return tc.getTicketById(id);
            } else {
                Ticket ticket = (Ticket) argObject;
                tc.getTicketById(ticket.getId()).updateTicket(ticket);
                return "Successfully updated element!";
            }
        });

        putCommand("remove_by_id", (argObject) -> {
            int id = (int) argObject;
            return tc.removeticketById(id) ? "Ticket successfully deleted!" : "Ticket with current id doesn't exists.";
        });

        putCommand("clear", (arg) -> {
            tc.clear();
            return "Collection cleared successfully!";
        });



        putCommand("remove_greater", (arg) -> {
            int id = (int) arg;
            return tc.removeGreater(id) ? "Greater tickets successfully deleted!" : "There are no tickets greater than this.";
        });
        putCommand("remove_by_refundable", (arg) -> {
            boolean ref = (boolean) arg;
            return tc.remove_by_refundable(ref) ? "Different tickets successfully deleted!" : "There are no different tickets.";
        });

        putCommand("remove_lower", (arg) -> {
            int id = (int) arg;
            return (tc.removeLower(id)) ? "Lower tickets successfully deleted!" : "There are no tickets lower than this.";
        });

        putCommand("filter_less_than_price", (arg) -> {
            int price = (int) arg;
            List<Ticket> subCollection = tc.getAl().stream()
                    .filter(ticket -> ticket.getPrice() < price)
                    .collect(Collectors.toList());

            if (subCollection.isEmpty()) {
                return "Tickets with less price was not found.";
            } else {
                StringBuilder sb = new StringBuilder("ID TICKET NAME");
                subCollection.forEach(ticket -> sb.append(String.format(Locale.US, " %d %f %s\n", ticket.getId(), ticket.getPrice(), ticket.getName())));
                return sb.toString();
            }
        });
        putCommand("remove_first", (argObject) -> {
            if (tc.size() == 0) {
                return "Collection is empty.";}
            tc.removeFirst();
            return "First element was removed!";
        });
        putCommand("remove_last", (argObject) -> {
            if (tc.size() == 0) {
                return "Collection is empty.";}
            tc.removeLast();
            return "Last element was removed!";
        });

        putCommand("show_by_less_type", (arg) -> {
            int type = TicketType.valueOf((String) arg).ordinal();
            List<Ticket> subCollection = tc.getAl().stream()
                    .filter(ticket -> ticket.getType().ordinal() < type)
                    .collect(Collectors.toList());
            if (subCollection.isEmpty()) {
                return "Tickets with less type was not found.";
            } else {
                TicketType a = TicketType.valueOf((String) arg);
                return tc.show_by_less_type(a);
            }
        });

        // Server command (Admins only)
        putCommand("ADMIN:save", (argObject) -> {
            String str = (String) argObject;
            if (str.equals("request_code")) {
                return sendRandotcode();
            } else if (str.equals(serverCode)) {
                fm.writeXMLFile(tc.getStartFilePath(), tc);
                return "Collection saved successfully!";
            }
            return "Invalid code: permission denied.";
        });

        putCommand("ADMIN:shutdown_server", (argObject) -> {
            String str = (String) argObject;
            if (str.equals("request_code")) {
                return sendRandotcode();
            } else if (str.equals(serverCode)) {
                fm.writeXMLFile(tc.getStartFilePath(), tc);
                System.exit(0);
            }
            return "Invalid code: permission denied.";
        });
    }

    private String sendRandotcode() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        serverCode = String.format("%06d", number);
        log.info("CODE TO ACCESS SERVER COMMAND: {}", serverCode);
        return "Enter the 6 digit code, that was shown in server's logs:";
    }
}
