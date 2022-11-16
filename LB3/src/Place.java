public class Place extends Enity{
    private String place;
    private String condition;
    public Place(String name) {
        super(name);
        System.out.printf("%s успешно создан \n", name);
    }
    public void PlaceCondition(Place place, Condition condition){
        System.out.printf("%s %s ", condition.getAction(), place.getName());
    }
    public void JustPlace(Place place){
        System.out.printf("%s ", place.getName());
    }
    public void PlaceConditionTwoPlaces(Place place, Place place2, Condition condition){
        System.out.printf("%s %s %s ", place.getName(), condition.getAction(), place2.getName());
    }



}
