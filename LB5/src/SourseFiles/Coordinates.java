package SourseFiles;

public class Coordinates {
    private float x; //Значение поля должно быть больше -390
    private Integer y; //Поле не может быть null
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
