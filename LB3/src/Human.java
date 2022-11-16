public class Human extends Enity{
    private String name;
    private Action action;
    private Condition condition;
    public Human(String name, Action action) {
        super(name, action);
        System.out.printf("%s успешно создан \n", name);
    }
    public Human(String name) {
        super(name);
        System.out.printf("%s успешно создан \n", name);
    }
    public void ActionWithoutTalking(Action action){
        System.out.print(action.getAction());
    }
    public void Action(Human name, Action action){
        System.out.printf("%s %s ", name.getName(), action.getAction());
    }
    public void ActionWithListening(Human name, Action action){
        System.out.printf("%s %s ", action.getAction(), name.getName());
    }
    public void Phrase(String phrase) {
        System.out.printf("%s", phrase);
    }
    public void ContactWithPlace(Human name, Action action, Condition condition, Place place){
        System.out.printf("%s %s %s %s ",name.getName(), action.getAction(), condition.getAction(), place.getName());
    }
    public void ContactWithPlaceWithoutPr(Human name, Action action, Place place){
        System.out.printf("%s %s %s ",name.getName(), action.getAction(), place.getName());
    }
    public void ContactWithPlaceWithoutAction(Human name, Place place){
        System.out.printf("%s %s ",name.getName(), place.getName());
    }
    public void ContactWithPlaceWithoutActionAndTalking(Condition condition, Place place){
        System.out.printf("%s %s ", condition.getAction(), place.getName());
    }
    public void ContactWithPlaceWithoutConditionAndTalking(Action action, Place place){
        System.out.printf("%s %s ", action.getAction(), place.getName());
    }
    public void ActionWithTwoPeople(Human name, Human name2, Action action){
        System.out.printf("%s %s %s ", name.getName(), name2.getName(), action.getAction());
    }

}
