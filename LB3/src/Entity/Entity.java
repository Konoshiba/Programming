package Entity;

import Humans.Human;
import java.util.Objects;

public abstract class Entity {
    protected final String name;
    protected final EnumGender gender;

    public Entity(EnumGender gender, String name) {
        this.gender = gender;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public EnumGender getGender() {
        return gender;
    }
    public void act(Verb verb){
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
        System.out.print("\n");
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
            int code = 0;
            for (int i=0; i<getName().length(); i++) code += getName().charAt(i);
            return code;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {return true;}
        if ( obj != null && obj.getClass() == this.getClass()) {return true;}
        Human name = (Human) obj;
        return Objects.equals(name.getName(), this.getName());
    }
}

