package Moves.Happiny;

import ru.ifmo.se.pokemon.*;

public class Refresh extends StatusMove {
    public Refresh(){
        super(Type.NORMAL, 0, 0);
    }

    @Override
    public void applySelfEffects(Pokemon pokemon) {
        if (pokemon.getCondition() == Status.BURN || pokemon.getCondition() == Status.PARALYZE || pokemon.getCondition() == Status.POISON) {
            Effect StatusClear = new Effect().condition(Status.NORMAL);
            pokemon.setCondition(StatusClear);
        }
    }
    @Override
    protected String describe(){
        return "использует Refresh";
    }
}
