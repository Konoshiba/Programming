import ru.ifmo.se.pokemon.Battle;
import Pokemon.*;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
        Blissey p1 = new Blissey("Унабомбер", 1);
        Chansey p2 = new Chansey("Мэддисон", 1);
        Crabrawler p3 = new Crabrawler("Ваномас", 1);
        Happiny p4 = new Happiny("Влад Савельев", 1);
        Pachirisu p5 = new Pachirisu("Светов", 1);
        Crabominable p6 = new Crabominable("Шлёпа", 1);
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}