package pl.edu.pg.po.projekt2.zwierzeta;

import pl.edu.pg.po.projekt2.Punkt;
import pl.edu.pg.po.projekt2.Swiat;
import pl.edu.pg.po.projekt2.Zwierze;

public class Wilk extends Zwierze {
    private static final char symbol = 'W';
    private static final int inicjatywa = 5;

    public Wilk(Swiat swiat, Punkt pole, int turaPowstania) {
        super(swiat, pole, turaPowstania, 9, inicjatywa, symbol);
        this.sila = 9;
        this.pole = pole;
        this.swiat = swiat;
        this.umarl = false;
        this.turaPowstania = turaPowstania;
    }

    @Override
    public String nazwaOrganizmu() {
        return "Wilk";
    }
}
