package Entity;

import Story.NullExeption;

public abstract class Alive extends Entity {

    protected Alive(EnumGender gender, String name){
        super(gender, name);
    }
    public String interact(Verb verb, Entity entity){
        return(interact(verb, entity, ""));
    }
    public String interact(Verb verb, String article, Adjective ... obj) {
        return(interact(verb, null, "", obj));
    }
    public String interact(Verb verb, Entity entity, String article, Adjective ... obj){
        if (verb == null){
            throw new NullExeption();
        }
        class Sentencebuilder{
            private String sentence ="";
            public void addword(String string){
                sentence += string + " ";
            }

            public String getSentence() {
                return sentence;
            }
        }
        Sentencebuilder sentencebuilder = new Sentencebuilder();
        sentencebuilder.addword(this.toString());
        switch(getGender()){
            case MALE:
                sentencebuilder.addword(verb.male());
                break;
            case FEMALE:
                sentencebuilder.addword(verb.female());
                break;
            case NEUTRAL:
                sentencebuilder.addword(verb.neutral());
                break;
            case PLURAL:
                sentencebuilder.addword(verb.plural());
                break;
        }
        if (article != ""){ sentencebuilder.addword(article);}
        if (entity != null) {
            sentencebuilder.addword(entity.toString());
        }
        for (int i = 0; i < obj.length; i++){
            if (obj[i].toString() != "") {
                sentencebuilder.addword(obj[i].toString());
            }
        }
        return sentencebuilder.getSentence();
    }
    public String interact(Verb verb, Entity entity, String article){
        return interact(verb, entity, article, new Adjective(""));
    }

}
