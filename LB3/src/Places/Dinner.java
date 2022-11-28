package Places;

import Entity.*;

public class Dinner extends Place implements InfActionWithFood{
    public Dinner(){ super(EnumGender.MALE, "Обед"); }
    @Override
    public void act_with_food(Verb verb, EnumFood ... obj) {
        System.out.print(this.toString() + " ");
        verb.male();
        System.out.print(" с ");
        for (int i = 0; i < obj.length; i++) {
            System.out.print(obj[i].getFood() + " ");
        }
        System.out.print("\n");
    }
    @Override
    public String toString() {
        return getName();
    }
}
