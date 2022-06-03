package pl.edu.pg.po.projekt2.rosliny;

import pl.edu.pg.po.projekt2.Punkt;
import pl.edu.pg.po.projekt2.Roslina;
import pl.edu.pg.po.projekt2.Swiat;

public class Trawa extends Roslina {
    private static final char symbol = 'T';
    private static final int inicjatywa = 0;
    private static final int sila = 0;

    public Trawa(Swiat swiat, Punkt pole, int turaPowstania) {
        super(swiat, pole, turaPowstania, sila, inicjatywa, symbol);
        this.pole = pole;
        this.swiat = swiat;
        this.umarl = false;
        this.turaPowstania = turaPowstania;
    }

    @Override
    public String nazwaOrganizmu() {
        return "Trawa";
    }
}
