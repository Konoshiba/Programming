package Entity;

public abstract class Alive extends Entity {

    protected Alive(EnumGender gender, String name){
        super(gender, name);
    }
    public void interact(Verb verb, Entity entity){
        interact(verb, entity, "");
    }
    public void interact(Verb verb, Entity entity, String article){
        System.out.print(this.toString() + " ");
        switch(getGender()){
            case MALE:
                verb.male();
                break;
            case FEMALE:
                verb.female();
                break;
            case NEUTRAL:
                verb.neutral();
                break;
            case PLURAL:
                verb.plural();
                break;
        }
        if (article != ""){ article += " "; }
        System.out.print(" " + article + entity + "\n");
    }
}
