package Entity;

import Humans.Human;
import Places.Place;

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
    // public void SetName(String name) {this.name = name;}
    // public void SetGender(EnumGender gender){this.gender = gender;}
    public String act(Verb verb){
        String result = "";
        result += this.toString() + " ";
        switch(getGender()){
            case MALE:
                result+=verb.male();
                break;
            case FEMALE:
                result+=verb.female();
                break;
            case NEUTRAL:
                result+=verb.neutral();
                break;
            case PLURAL:
                result+=verb.plural();
                break;
        }
        return result + " ";
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
    public static class Air extends Entity {
        public Air(){ super(EnumGender.MALE, "Воздух"); }
    }


    public static class Arm extends Entity{
        public Arm(){ super(EnumGender.FEMALE, "Рука"); }
    }
    public static class Rag extends Entity {
        public Rag(){ super(EnumGender.FEMALE, "Тряпка"); }
    }
    public static class Luck extends Entity {
        public Luck(){ super(EnumGender.NEUTRAL, "Везение"); }
    }
    public static class Begonia extends Entity {
        public Begonia(){ super(EnumGender.FEMALE, "Бегония"); }
    }
    public static class Flood extends Entity {
        public Flood(){ super(EnumGender.NEUTRAL, "Наводнение"); }
    }
    public static class Ticket extends Entity {
        public Ticket(){ super(EnumGender.NEUTRAL, "Билет"); }
    }
}

