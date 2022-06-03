package pl.edu.pg.po.projekt2.zwierzeta;

import pl.edu.pg.po.projekt2.Punkt;
import pl.edu.pg.po.projekt2.Swiat;
import pl.edu.pg.po.projekt2.Zwierze;

public class Owca extends Zwierze {
    private static final char symbol = 'O';
    private static final int inicjatywa = 4;

    public Owca(Swiat swiat, Punkt pole, int turaPowstania) {
        super(swiat, pole, turaPowstania, 4, inicjatywa, symbol);
        this.sila = 4;
        this.pole = pole;
        this.swiat = swiat;
        this.umarl = false;
        this.turaPowstania = turaPowstania;
    }

    @Override
    public String nazwaOrganizmu() {
        return "Owca";
    }
}
