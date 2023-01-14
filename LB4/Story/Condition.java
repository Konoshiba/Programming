package Story;

import Entity.*;

public class Condition {
    Verb goaway = new Verb("уйти"){
        @Override
        public String male(){
            return "уйдет";
        }
    };
    Verb sail = new Verb("уплыть"){
        @Override
        public String plural(){
            return "уплывут";
        }
    };
    Verb test = new Verb("испытать");
    Verb ask = new Verb("спросить");
    Verb notbe = new Verb("отсутствовать");
    ReturnableVerb retrn = new ReturnableVerb("вернуться");
    ReturnableVerb angry = new ReturnableVerb("рассердиться");
    ReturnableVerb clean = new ReturnableVerb("убраться");
    ReturnableVerb run = new ReturnableVerb("помчаться");
    Verb swoop = new Verb("спикировать");
    Verb detect = new Verb("заметить");

    Verb hiss = new Verb("засвистеть");
    Verb wave = new Verb("замахать");
    Verb fly = new Verb("улететь");
    Verb become = new Verb("собирать");
    Verb use = new Verb("использовать");

    Verb understand = new Verb("понять");
    Verb buy = new Verb("купить");
    Verb said = new Verb("рассказать");
    Verb give = new Verb("подать");
    ReturnableVerb try_to = new ReturnableVerb("постараться");
    Verb mess_up = new Verb("натворить");
    Verb lunch = new Verb("обедать");
    Verb think = new Verb("думать");
    Verb dont_eat = new Verb("не кушать");
    ReturnableVerb fun = new ReturnableVerb("веселиться");
    Verb sit = new Verb("сидеть");
    Verb say = new Verb("болтать");
    Verb leave = new Verb("уезжать");
    ReturnableVerb study = new ReturnableVerb("учиться");
    Verb be = new Verb("быть");
}
