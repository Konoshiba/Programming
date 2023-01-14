package Story;

import Animals.Horses;
import Entity.*;
import Humans.*;
import Places.*;

public final class Story extends Condition{
    public class Paragraph{
        private String content = "";
        public void printparagraph(){
            System.out.println(content);
        }
        public void addsentence(String sentence, boolean comma){
            content+=sentence.trim();
            if (comma){
                content+=", ";
            }
            else{
                content+=". ";
            }
        }
        public void addsentence(String sentence){
            addsentence(sentence, false);
        }
    }
    Human.He he = new Human.He();
    Human.She she = new Human.She();
    Human.It it = new Human.It();
    Karlson karlson = new Karlson();
    Place.Window window = new Place.Window();
    Place.Air air = new Place.Air();
    Place.Rag rag = new Place.Rag();
    Place.Arm arm = new Place.Arm();
    Place.Water water = new Place.Water();
    Place.Hallway hallway = new Place.Hallway();
    Adjective adjrag = new Adjective(rag, use, EnumGender.MALE);
    Adjective adjhallway = new Adjective(hallway, "в");


    Mother mother = new Mother();
    Who who = new Who();
    Father father = new Father();
    Baby baby = new Baby();
    Human.All all = new Human.All();
    Place.Dinner dinner = new Place.Dinner();
    Place.Table table = new Place.Table();
    Bosse bosse = new Bosse();
    Betan betan = new Betan();
    Place.YachtClub yacht_club = new Place.YachtClub();
    Place.Sailing sailing = new Place.Sailing();
    Place.Hutor hutor = new Place.Hutor();
    Horses horses = new Horses();
    Place.FarewellDinner farewell_dinner = new Place.FarewellDinner();
    Place.Feast feast = new Place.Feast();
    Entity.Luck luck = new Entity.Luck();
    Place.Bureau bureau = new Place.Bureau();
    Entity.Begonia begonia = new Entity.Begonia();


    Adjective adjbegonia = new Adjective(begonia, "из-за");
    Entity.Flood flood = new Entity.Flood();
    Adjective adjflood = new Adjective(flood, "из-за");
    Place.Boat boat = new Place.Boat();
    Adjective adjboat = new Adjective(boat, "на");
    Entity.Ticket ticket = new Entity.Ticket();
    public void run(){
        defaultStory();
    }
    public void defaultStory(){
        try {


            Paragraph paragraph1 = new Paragraph();
            paragraph1.addsentence(karlson.interact(detect, baby));
            paragraph1.addsentence(he.act(swoop));
            paragraph1.addsentence(he.interact(run, window, "мимо"));
            paragraph1.addsentence(air.act(hiss));
            paragraph1.printparagraph();
            Paragraph paragraph2 = new Paragraph();
            paragraph2.addsentence(baby.interact(wave, rag));
            paragraph2.addsentence(karlson.interact(wave, arm));
            paragraph2.addsentence(he.act(fly));
            paragraph2.addsentence(baby.interact(become, water, "", adjrag, adjhallway));
            paragraph2.printparagraph();
            Header.headermanager.header1();
            Paragraph paragraph3 = new Paragraph();
            paragraph3.addsentence(karlson.interact(test, luck));
            paragraph3.addsentence(he.act(notbe));
            paragraph3.addsentence(mother.interact(retrn, bureau, "из"));
            paragraph3.addsentence(mother.interact(angry, "", adjbegonia, adjflood));
            paragraph3.addsentence(baby.act(clean));
            paragraph3.printparagraph();
            Paragraph paragraph4 = new Paragraph();
            paragraph4.addsentence(mother.act(understand));
            paragraph4.addsentence(who.act(mess_up));
            paragraph4.addsentence(mother.interact(said, father));
            paragraph4.addsentence(father.act(lunch));
            paragraph4.printparagraph();
            Paragraph paragraph5 = new Paragraph();
            paragraph5.addsentence(mother.saidPhrase(mother));
            paragraph5.addsentence(baby.act(think));
            paragraph5.addsentence(all.act(fun));
            paragraph5.addsentence(all.interact(sit, table, "за"));
            paragraph5.printparagraph();
            Paragraph paragraph6 = new Paragraph();
            paragraph6.addsentence(all.act(say));
            paragraph6.addsentence(baby.act(say));
            paragraph6.addsentence(baby.act(dont_eat));
            paragraph6.addsentence(dinner.actWithFood(be, EnumFood.FISH, EnumFood.SOUP, EnumFood.CUTLETS));
            paragraph6.printparagraph();
            Paragraph paragraph7 = new Paragraph();
            try {
                paragraph7.addsentence(mother.actWithFood(give, EnumFood.CHOPS, EnumFood.STRAWBERRY));
            }
            catch (BadFoodExeption e){
                paragraph7.addsentence("Мама что-то напутала и ничего не приготовила");
            }
            paragraph7.addsentence(bosse.act(leave));
            paragraph7.addsentence(betan.act(leave));
            paragraph7.addsentence(bosse.interact(leave, yacht_club, "в"));
            paragraph7.printparagraph();
            Paragraph paragraph8 = new Paragraph();
            paragraph8.addsentence(bosse.interact(study, sailing, ""));
            paragraph8.addsentence(betan.interact(leave, hutor, "на"));
            paragraph8.addsentence(horses.interact(be, hutor, "на"));
            paragraph8.addsentence(farewell_dinner.act(be));
            paragraph8.printparagraph();
            Paragraph paragraph9 = new Paragraph();
            paragraph9.addsentence(mother.act(try_to));
            paragraph9.addsentence(feast.act(be));
            paragraph9.addsentence(he.act(said));
            paragraph9.addsentence(mother.interact(be, bureau, "в"));
            paragraph9.printparagraph();
            Paragraph paragraph10 = new Paragraph();
            paragraph10.addsentence(mother.interact(buy, ticket, "", adjboat));
            paragraph10.addsentence(boat.act(goaway));
            paragraph10.addsentence(all.interact(sail, boat, "на"));
            paragraph10.addsentence(father.interact(ask, he));
            paragraph10.printparagraph();
            Paragraph paragraph11 = new Paragraph();
            paragraph11.addsentence(bosse.act(ask));
            paragraph11.addsentence(betan.act(ask));
            paragraph11.printparagraph();
        }
        catch (NullExeption e){
            System.out.println("Кто-то испортил код своим null");
        }
        Paragraph paragraph11 = new Paragraph();
        //paragraph11.addsentence(betan.interact(null, boat, "на"));
        paragraph11.printparagraph();
    }
}
