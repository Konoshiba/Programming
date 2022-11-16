package Moves.Happiny;

import ru.ifmo.se.pokemon.*;

public class Confide extends StatusMove {
    public Confide(){
        super(Type.NORMAL, 0, 0);
    }

    @Override
    public void applyOppEffects(Pokemon pokemon){
        Effect LowersSpecialAttack = new Effect().stat(Stat.SPECIAL_ATTACK, -1);
        pokemon.addEffect(LowersSpecialAttack);
    }

    @Override
    protected String describe(){
        return "использует Confide";
    }

}
