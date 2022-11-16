public enum Action {
    Understand("понял(-а) "),
    MessUp("натворил(а) "),
    Fun("было весело когда "),
    Eat("а не ел(-а) "),
    Speak("говорил(-а) "),
    Sit("сидел(-и)"),
    Talk("и болтал(-и) "),
    Tell("и рассказал(-а)"),
    Repeat("повторял(-а)"),
    Come("когда он(-а) пришел(-ла) "),
    Lunch("обедать "),

    Think("также думал "),
    Give("подал(-а) "),
    Leave("уезжал(-и) "),
    Study("учиться "),
    TryHard("посталался(-лась) "),
    ;
    private String phrase;
    Action(final String phrase){
        this.phrase = phrase;
    }
    public String getAction(){return phrase;}
    public String getPhrase(){return phrase;}
}
