package Moves.Crabominable;

import ru.ifmo.se.pokemon.*;

public class Ice_Punch extends PhysicalMove {
    public Ice_Punch(){
        super(Type.ICE, 75, 100);
    }

    @Override
    public void applyOppEffects(Pokemon pokemon){
        Effect Freeze = new Effect().chance(0.1).condition(Status.FREEZE);
        pokemon.addEffect(Freeze);
    }

    @Override
    protected String describe(){
        return "использует Confide";
    }
}
