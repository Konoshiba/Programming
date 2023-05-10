package data;

import java.io.Serializable;

public class Coordinates implements Serializable {
    private float x; //Значение поля должно быть больше -390
    private Integer y; //Поле не может быть null
    public Coordinates(Float x, Integer y) {
        this.x = x;
        this.y = y;
    }
    public void setX(float x){
        this.x = x;
    }

    public float getX() {
        return x;
    }

    public void setY(Integer y){
        this.y = y;
    }

    public Integer getY() {
        return y;
    }
}
