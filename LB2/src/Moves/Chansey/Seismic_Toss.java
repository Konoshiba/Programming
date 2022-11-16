package Moves.Chansey;

import ru.ifmo.se.pokemon.*;

public class Seismic_Toss extends PhysicalMove {
    public Seismic_Toss(int level){
        super(Type.FIGHTING, level, 100);
    }
    @Override
    protected String describe() {
        return "использует Seimic_Toss";
    }
}
