package Moves.Crabrawler;
import ru.ifmo.se.pokemon.*;

public class Leer extends PhysicalMove {
    public Leer() {
        super(Type.NORMAL, 0, 100);
    }

    @Override
    public void applyOppEffects(Pokemon pokemon){
        Effect LowersDefense = new Effect().stat(Stat.DEFENSE, -1);
        pokemon.addEffect(LowersDefense);
    }

    @Override
    protected String describe(){
        return "использует Leer";
    }
}
