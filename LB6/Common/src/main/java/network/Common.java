package network;

import console.Console;
import console.InputValidator;
import data.*;
import exceptions.ExecuteScriptFailedException;

import java.util.function.Supplier;

public class Common {
    public final static int PORT = 8000;

    public static Ticket inputAndUpdateticket(boolean updMode, Ticket ticket, boolean printMode, Supplier<String> valueGetter) throws ExecuteScriptFailedException {
        String ticketName = (String) new InputValidator(String.class, false)
                .loadPreviousValue(updMode, updMode ? ticket.getName() : null)
                .interactiveInput("ticket name", printMode, valueGetter);

        Console.println("Type coordinates:", printMode);
        Float x = (float) new InputValidator(float.class, false, -390, Integer.MAX_VALUE)
                .loadPreviousValue(updMode, updMode ? ticket.getCoordinates().getX() : null)
                .interactiveInput("x (float > -390)", printMode, valueGetter);

        Integer y = (int) new InputValidator(int.class, false)
                .loadPreviousValue(updMode, updMode ? ticket.getCoordinates().getY() : null)
                .interactiveInput("y (integer)", printMode, valueGetter);

        Coordinates coordinates = new Coordinates(x,y);

        float price = (float) new InputValidator(float.class, false, 0, Float.MAX_VALUE)
                .loadPreviousValue(updMode, updMode ? ticket.getPrice() : null)
                .interactiveInput("price", printMode, valueGetter);

        TicketType ticketType = (TicketType) new InputValidator(TicketType.class, false)
                .loadPreviousValue(updMode, updMode ? ticket.getType() : null)
                .interactiveInput("ticket type", TicketType.values(), printMode, valueGetter);

        boolean refundable = (boolean) new InputValidator(boolean.class, false, 0, 1)
                .loadPreviousValue(updMode, updMode ? ticket.getRefundable() : null)
                .interactiveInput("refundable", printMode, valueGetter);

        String venueName = (String) new InputValidator(String.class, false)
                .loadPreviousValue(updMode, updMode ? ticket.getVenue().getName() : null)
                .interactiveInput("venue name", printMode, valueGetter);

        Integer capacity = (int) new InputValidator(int.class, false)
                .loadPreviousValue(updMode, updMode ? ticket.getVenue().getCapacity() : null)
                .interactiveInput("capacity", printMode, valueGetter);

        VenueType venueType = (VenueType) new InputValidator(VenueType.class, false)
                .loadPreviousValue(updMode, updMode ? ticket.getVenue().getType() : null)
                .interactiveInput("venue type", VenueType.values(), printMode, valueGetter);


        String comment = (String) new InputValidator(String.class, true, 0, 404)
                .loadPreviousValue(updMode, updMode ? ticket.getVenue().getName() : null)
                .interactiveInput("comment", printMode, valueGetter);


        Venue venue = new Venue(venueName, capacity, venueType);

        if (updMode) {
            ticket.updateTicket(ticketName, coordinates, price, refundable, ticketType, venue, comment);
        } else {
            ticket = new Ticket(ticketName, coordinates, price, refundable, ticketType, venue, comment);
        }
        return ticket;
    }
}
