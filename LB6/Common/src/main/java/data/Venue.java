package data;

import java.io.Serializable;

public class Venue implements Comparable<Venue>, Serializable {
    private static int counter = 0;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Integer capacity; //Поле не может быть null, Значение поля должно быть больше 0
    private VenueType type; //Поле может быть null
    public Venue(String name, Integer capacity, VenueType type) {
        this.id = ++counter;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setType(VenueType type) {
        this.type = type;
    }

    public VenueType getType() {
        return type;
    }
    @Override
    public int compareTo(Venue v) {
        return Double.compare(getCapacity(), v.getCapacity());
    }
}