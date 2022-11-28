package Entity;

public class ReturnableVerb extends Verb {
    public ReturnableVerb(String action){
        super(action.substring(0, action.length() - 2));
    }
    public void male(){
        System.out.print(action_with_gender + "лся");
    }
    public void female(){
        System.out.print(action_with_gender + "лась");
    }
    public void neutral(){
        System.out.print(action_with_gender + "лось");
    }
    public void plural(){
        System.out.print(action_with_gender + "лись");
    }
}
