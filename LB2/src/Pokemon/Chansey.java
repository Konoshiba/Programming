package Pokemon;

import Moves.Chansey.Seismic_Toss;

public class Chansey extends Happiny{
    public Chansey(String Chansey, int level){
        super(Chansey, level);
        setStats(250, 5, 5, 35, 105, 50);
        addMove(new Seismic_Toss(level));
    }
}
