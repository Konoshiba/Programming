package Moves.Pachirisu;

import ru.ifmo.se.pokemon.*;

public class Sweet_Kiss extends StatusMove {
    public Sweet_Kiss() {
        super(Type.FLYING, 0, 0.75);
    }

    @Override
    public void applyOppEffects(Pokemon pokemon) {
        Effect.confuse(pokemon);
    }

    @Override
    protected String describe(){
        return "использует Sweet_Kiss";
    }

}
