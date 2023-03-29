package Commands;

public class Help extends Command{
    public Help(){
        super("help", "show full list of commands");
    }
    @Override
    public boolean execute(String arg){
            System.out.println(arg);
        return true;
    }
}
