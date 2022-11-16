package Moves.Pachirisu;

import ru.ifmo.se.pokemon.*;

public class Nuzzle extends PhysicalMove {
    public Nuzzle() {
        super(Type.ELECTRIC, 20, 1);
    }

    @Override
    public void applyOppEffects(Pokemon pokemon){
        Effect.paralyze(pokemon);
    }

    @Override
    protected String describe(){
        return "использует Nuzzle";
    }
}
