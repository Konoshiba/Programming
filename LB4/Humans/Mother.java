package Humans;

import Entity.*;
import Story.BadFoodExeption;

public class Mother extends Human implements InfSaid, InfActionWithFood {
    public Mother(){
        super(EnumGender.FEMALE, "Мама");
    }
    @Override
    public String saidPhrase(Human mother) {
        return (mother.getName() + ": \"Во время еды надо, чтобы было весело\"");
    }

    @Override
    public String actWithFood(Verb verb, EnumFood ... obj) throws BadFoodExeption {
        String result="";
        result+=this.act(verb);
        for (int i = 0; i < obj.length; i++){
            if (obj[i] == EnumFood.CAL){
                throw new BadFoodExeption();
            }
            result+=(obj[i].getFood() + " ");
        }
        return result;
    }
}
