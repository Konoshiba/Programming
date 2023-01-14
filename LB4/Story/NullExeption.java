package Story;

public class NullExeption extends RuntimeException{
    public NullExeption(){
        super("У вас null в verb, но не в блоке кода, где ловятся ошибки");
    }

}
