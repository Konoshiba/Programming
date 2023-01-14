package Entity;

public class ReturnableVerb extends Verb {
    public ReturnableVerb(String action){
        super(action.substring(0, action.length() - 2));
    }
    public String male(){
        return (action_with_gender + "лся");
    }
    public String female(){
        return (action_with_gender + "лась");
    }
    public String neutral(){
        return (action_with_gender + "лось");
    }
    public String plural(){
        return (action_with_gender + "лись");
    }
}
