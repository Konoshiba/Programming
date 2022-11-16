package Moves.Crabrawler;

import ru.ifmo.se.pokemon.*;

public class Iron_Defense extends PhysicalMove {
    public Iron_Defense(){
        super(Type.STEEL, 0, 0);
    }

    @Override
    public void applySelfEffects(Pokemon pokemon){
        Effect RaisesDefense = new Effect().stat(Stat.DEFENSE, +2);
        pokemon.addEffect(RaisesDefense);
    }

    @Override
    protected String describe(){
        return "использует Iron_Defence";
    }
}
