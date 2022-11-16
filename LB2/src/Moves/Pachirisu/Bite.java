package Moves.Pachirisu;

import ru.ifmo.se.pokemon.*;
import static java.lang.Math.*;

public class Bite extends PhysicalMove {
    public Bite() {

        super(Type.DARK, 60, 1);
    }

    @Override
    public void applyOppEffects(Pokemon pokemon){
        if(random() < 0.3) {
            Effect.flinch(pokemon);
        }
    }

    @Override
    protected String describe(){
        return "использует Byte";
    }
}
