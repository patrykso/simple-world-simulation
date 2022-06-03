package pl.edu.pg.po.projekt2.rosliny;

import pl.edu.pg.po.projekt2.Punkt;
import pl.edu.pg.po.projekt2.Roslina;
import pl.edu.pg.po.projekt2.Swiat;

public class WilczeJagody extends Roslina {
    private static final char symbol = 'J';
    private static final int inicjatywa = 0;
    private static final int sila = 99;

    public WilczeJagody(Swiat swiat, Punkt pole, int turaPowstania) {
        super(swiat, pole, turaPowstania, sila, inicjatywa, symbol);
        this.pole = pole;
        this.swiat = swiat;
        this.umarl = false;
        this.turaPowstania = turaPowstania;
    }

    @Override
    public String nazwaOrganizmu() {
        return "Wilcze Jagody";
    }
}
