package Entity;

public enum EnumFood {

    FISH("вареная треска"),
    SOUP("овощной суп"),
    CUTLETS("селедочные котлеты"),
    CHOPS("телячьи отбивные"),
    STRAWBERRY("клубника"),
    CAL("говно");
    private String food;
    EnumFood(final String food){
        this.food = food;
    }
    public String getFood(){return food;}
    @Override
    public String toString(){
        return getFood();
    }
}
