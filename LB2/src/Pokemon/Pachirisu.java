package Pokemon;
import Moves.Pachirisu.*;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Pachirisu extends Pokemon{
    public Pachirisu(String Pachirisu, int level) {
        super(Pachirisu, level);
        super.setType(Type.ELECTRIC);
        setStats(60, 45, 70, 45, 90, 95);
        setMove(new Bite(), new Nuzzle(), new Sweet_Kiss(), new Swift());
    }

}
