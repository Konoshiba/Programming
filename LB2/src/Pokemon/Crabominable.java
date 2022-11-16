package Pokemon;

import Moves.Crabominable.Ice_Punch;

public class Crabominable extends Crabrawler{
    public Crabominable(String Crabominable, int level){
        super(Crabominable, level);
    setStats(97, 132, 77, 62, 67, 43);
    addMove(new Ice_Punch());
    }
}
