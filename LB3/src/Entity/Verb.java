package Entity;

public class Verb implements InfDeclension {
    String action_with_gender;
    public Verb(String action){
        this.action_with_gender = action.substring(0, action.length() - 2);
    }
    public void male(){
        System.out.print(action_with_gender + "л");
    }
    public void female(){
        System.out.print(action_with_gender + "ла");
    }
    public void neutral(){
        System.out.print(action_with_gender + "ло");
    }
    public void plural(){
        System.out.print(action_with_gender + "ли");
    }


}
