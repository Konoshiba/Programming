package Pokemon;
import Moves.Crabrawler.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Crabrawler extends Pokemon{
    public Crabrawler(String Crabrawler, int level){
        super(Crabrawler, level);
        setType(Type.FIGHTING);
        setStats(47, 82, 57, 42, 47, 63);
        setMove(new Confide(), new Iron_Defense(), new Leer());
    }
}
