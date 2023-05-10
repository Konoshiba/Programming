package data;

import exceptions.InvalidRangeException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

public class Ticket implements Comparable<Ticket>, Serializable {
    private static int counter = 0;
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private String comment; //Длина строки не должна быть больше 404, Поле не может быть null
    private Boolean refundable; //Поле не может быть null
    private TicketType type; //Поле может быть null
    private Venue venue; //Поле не может быть null
    public Ticket() {
        this.id = 0;
        this.creationDate = LocalDateTime.now();
    }

    public Ticket(@NotNull String name, @NotNull Coordinates coordinates,
                  @NotNull float price, @NotNull Boolean refundable, @NotNull TicketType type, @NotNull Venue venue, String comment){
        this.id = ++counter;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.price = price;
        this.refundable = refundable;
        this.type = type;
        this.venue = venue;
        this.comment = comment;
    }
    public void updateTicket(@NotNull String name, @NotNull Coordinates coordinates,
                      @NotNull float price, @NotNull Boolean refundable, @NotNull TicketType type, @NotNull Venue venue, String comment){
        this.name = name;
        this.coordinates = coordinates;
        this.price = price;
        this.refundable = refundable;
        this.type = type;
        this.venue = venue;
        this.comment = comment;
    }

    public void updateTicket(Ticket t) {
        this.name = t.name;
        this.coordinates = t.coordinates;
        this.price = t.price;
        this.refundable = t.refundable;
        this.type = t.type;
        this.venue = t.venue;
        this.comment = t.comment;
    }

    public static void setCounter(int c){
       // if (c < counter) throw new InvalidRangeException("New counter must be bigger than previous!");
        counter = c;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCreationDate(java.time.LocalDateTime creationDate){
        this.creationDate = creationDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setComment(String comment){
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setRefundable(Boolean refundable){
        this.refundable = refundable;
    }

    public Boolean getRefundable() {
        return refundable;
    }

    public void setType(TicketType type){
        this.type = type;
    }

    public TicketType getType() {
        return type;
    }

    public void setVenue(Venue venue){
        this.venue = venue;
    }

    public Venue getVenue() {
        return venue;
    }

    @Override
    public int compareTo(Ticket t) {
        return Integer.compare(getType().ordinal(), t.getType().ordinal());
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "%d %s", id, name);
    }
}
