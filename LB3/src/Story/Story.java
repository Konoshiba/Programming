package Story;

import Animals.Horses;
import Entity.*;
import Humans.*;
import Places.*;

public class Story extends Condition{
    Mother mother = new Mother();
    Who who = new Who();
    Father father = new Father();
    Baby baby = new Baby();
    All all = new All();
    Dinner dinner = new Dinner();
    Table table = new Table();
    Bosse bosse = new Bosse();
    Betan betan = new Betan();
    YachtClub yacht_club = new YachtClub();
    Sailing sailing = new Sailing();
    Hutor hutor = new Hutor();
    Horses horses = new Horses();
    FarewellDinner farewell_dinner = new FarewellDinner();
    Feast feast = new Feast();

    public void run(){
        defaultStory();
    }
    public void defaultStory(){
        mother.act(understand);
        who.act(mess_up);
        mother.interact(said, father);
        father.act(lunch);
        mother.said_phrase(mother);
        baby.act(think);
        all.act(fun);
        all.interact(sit, table, "за");
        all.act(say);
        baby.act(say);
        baby.act(dont_eat);
        dinner.act_with_food(be, EnumFood.FISH, EnumFood.SOUP, EnumFood.CUTLETS);
        mother.act_with_food(give, EnumFood.CHOPS, EnumFood.STRAWBERRY);
        bosse.act(leave);
        betan.act(leave);
        bosse.interact(leave, yacht_club, "в");
        bosse.interact(study, sailing, "");
        betan.interact(leave, hutor, "на");
        horses.interact(be, hutor, "на");
        farewell_dinner.act(be);
        mother.act(try_to);
        feast.act(be);
    }
}
