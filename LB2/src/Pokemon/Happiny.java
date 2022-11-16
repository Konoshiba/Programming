package Pokemon;
import Moves.Happiny.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Happiny extends Pokemon{
    public Happiny(String Happiny, int level){
        super(Happiny, level);
        setType(Type.NORMAL);
        setStats(100, 5, 5, 15, 65, 30);
        setMove(new Confide(), new Refresh());
    }
}
