package Pokemon;

import Moves.Blissey.Double_Edge;

public class Blissey extends Chansey{
    public Blissey(String Blissey, int level) {
        super(Blissey, level);
        setStats(255, 10, 10, 75, 135, 55);
        addMove(new Double_Edge());
    }
}
