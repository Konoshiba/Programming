package Moves.Blissey;

import ru.ifmo.se.pokemon.*;

public class Double_Edge extends PhysicalMove {
    public Double_Edge(){
        super(Type.NORMAL, 120, 100);
    }

    @Override
    public void applySelfEffects(Pokemon pokemon){
        Effect UserReceivesDamage = new Effect().stat(Stat.HP, (int)(pokemon.getHP())-(int)(pokemon.getStat(Stat.ATTACK)/3));
        pokemon.addEffect(UserReceivesDamage);
    }


    @Override
    protected String describe(){
        return "использует Double_Edge";
    }
}
