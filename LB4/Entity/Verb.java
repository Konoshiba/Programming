package Entity;

public class Verb implements InfDeclension {
    String action_with_gender;
    String inf;
    public Verb(String action){
        this.action_with_gender = action.substring(0, action.length() - 2);
        this.inf = action;
    }
    public String male(){
        return (action_with_gender + "л");
    }
    public String female(){
        return (action_with_gender + "ла");
    }
    public String neutral(){
        return (action_with_gender + "ло");
    }
    public String plural(){
        return (action_with_gender + "ли");
    }
    @Override
    public String toString() {
        return inf;
    }


}
