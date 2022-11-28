package Humans;

import Entity.*;

public class Mother extends Human implements InfSaid, InfActionWithFood {
    public Mother(){
        super(EnumGender.FEMALE, "Мама");
    }
    @Override
    public void said_phrase(Human mother) {
        System.out.println(mother.getName() + ": \"Во время еды надо, чтобы было весело\"");
    }

    @Override
    public void act_with_food(Verb verb, EnumFood ... obj) {
        this.act(verb);
        for (int i = 0; i < obj.length; i++){
            System.out.print(obj[i].getFood() + " ");
        }

    }
}
