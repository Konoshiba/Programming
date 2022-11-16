import java.util.Objects;

public abstract class Enity implements InfEnity {
    private final String name;
    private Action action;

    public Enity(String name) {
        this.name = name;
    }
    public Enity(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public Action getAction() {
        return action;
    }
    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString(){
        return getName();
    }
    @Override
    public int hashCode() {
        int buff = 0;

        for (byte i=0; i<this.getName().length(); i++) buff += this.getName().charAt(i);
        for (byte i=0; i<this.getAction().toString().length(); i++) buff += getAction().toString().charAt(i);

        return buff;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {return true;}
        if (this.getClass() == obj.getClass()) {return true;}

        Enity check = (Enity) obj;
        return this.hashCode() == check.hashCode();
    }
}

