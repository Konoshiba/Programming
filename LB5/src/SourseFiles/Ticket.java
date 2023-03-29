package SourseFiles;

import java.time.LocalDateTime;

public class Ticket {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private String comment; //Длина строки не должна быть больше 404, Поле не может быть null
    private Boolean refundable; //Поле не может быть null
    private TicketType type; //Поле может быть null
    private Venue venue; //Поле не может быть null

    public Ticket(Integer id, String name, Coordinates coordinates, java.time.LocalDateTime creationDate,
                  float price, Boolean refundable, TicketType type, Venue venue, String comment){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.refundable = refundable;
        this.type = type;
        this.venue = venue;
        this.comment = comment;
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

    public int compareTo(Ticket other){
        int resultByPrice = (int) (this.price - other.price);
        int resultByType =  (this.type.ordinal() - other.type.ordinal());
        if (resultByPrice == 0){
            if (resultByType == 0){
                return 0;
            }
            else {
                return -resultByType;
            }
        }
        else{
            return resultByPrice;
        }
    }
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + " X=" +coordinates.getX() + " Y=" + coordinates.getY() +
                ", creationDate=" + creationDate +
                ", Price=" + price +
                ",refundable=" + refundable +
                ", type=" + type +
                ", Venue='" + venue.getName() + '\'' +
                ", Comment='" + comment + "'\n" +
                "Venue{" +
                "id=" + venue.getId() +
                ", name='" + venue.getName() + '\'' +
                ", сapacity='" + venue.getCapacity() +
                ", type='" + venue.getType() +
                '}';
    }
}
