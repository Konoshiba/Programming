package Moves.Crabrawler;

import ru.ifmo.se.pokemon.*;

public class Confide extends PhysicalMove {
    public Confide(){
        super(Type.NORMAL, 0, 0);
    }

    @Override
    public boolean checkAccuracy(Pokemon att, Pokemon def){
        return true;
    }

    @Override
    public void applyOppEffects(Pokemon pokemon) {
        Effect Lowers_Special_Attack = new Effect().stat(Stat.SPECIAL_ATTACK, -1);
        pokemon.addEffect(Lowers_Special_Attack);
    }

    @Override
    protected String describe(){
        return "использует Confide";
    }
}
