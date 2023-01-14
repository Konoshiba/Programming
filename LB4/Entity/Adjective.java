package Entity;

import Places.Place;

public class Adjective {
    String name = "";
    public Adjective(String string){
        this.name = string;
    }

    public Adjective(Entity entity, Verb verb, EnumGender gender){
        switch(gender){
            case MALE:
                name = verb.male() + " " + entity.toString();
                break;
            case FEMALE:
                name = verb.female() + " " + entity.toString();
                break;
            case NEUTRAL:
                name = verb.neutral() + " " + entity.toString();
                break;
            case PLURAL:
                name = verb.plural() + " " + entity.toString();
                break;
        }

    }

    public Adjective(Entity entity, String article){
        this(article + " " + entity.toString());
    }
    public String toString(){
        return name;
    }
}
