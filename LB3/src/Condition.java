public enum Condition {
    Behind("за"),
    On("на"),
    Start("начался(-лись)"),
    From("из"),
    In("в"),
    Is("есть"),
    Was("был"),
    TurnedInto("превратился"),
    ;
    private String phrase;
    Condition(final String phrase){
        this.phrase = phrase;
    }
    public String getAction(){return phrase;}
    public String getPhrase(){return phrase;}
}
