package Entity;

import Story.BadFoodExeption;

public interface InfActionWithFood {
    public String actWithFood(Verb verb, EnumFood ... obj) throws BadFoodExeption;
}
