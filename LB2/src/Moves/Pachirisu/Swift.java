package Moves.Pachirisu;

import ru.ifmo.se.pokemon.*;

public class Swift extends SpecialMove {
    public Swift() {
        super(Type.NORMAL, 60, 1);
    }

    @Override
    public boolean checkAccuracy(Pokemon att, Pokemon def){
        return true;
    }

    @Override
    protected String describe(){
        return "использует Swift";
    }
}
