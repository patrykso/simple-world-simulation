package pl.edu.pg.po.projekt2.zwierzeta;

import pl.edu.pg.po.projekt2.Organizm;
import pl.edu.pg.po.projekt2.Punkt;
import pl.edu.pg.po.projekt2.Swiat;
import pl.edu.pg.po.projekt2.Zwierze;

public class Lis extends Zwierze {
    private static final char symbol = 'L';
    private static final int inicjatywa = 7;

    public Lis(Swiat swiat, Punkt pole, int turaPowstania) {
        super(swiat, pole, turaPowstania, 3, inicjatywa, symbol);
        this.sila = 3;
        this.pole = pole;
        this.swiat = swiat;
        this.umarl = false;
        this.turaPowstania = turaPowstania;
    }

    @Override
    public void akcja(Punkt pole) {
        Punkt nowePole = new Punkt(-2, -2);
        while(true) {
            nowePole = losujPunktWZasiegu(pole, 1);
            if (nowePole.getY() >= swiat.getY() || nowePole.getY() < 0) continue;
            else if (nowePole.getX() >= swiat.getX() || nowePole.getX() < 0) continue;
            else if(nowePole.getX() == -4 && nowePole.getY() == -4) return;
            else if (!swiat.silniejszyOrganizm(nowePole, inicjatywa)) break; //akcja specjalna lisa
            else break;
        }
        if (swiat.czyPoleZajete(nowePole)) {
            swiat.getPole(nowePole).kolizja(this);
        }
        else {
            swiat.przemiescOrganizm(this, nowePole);
        }
    }

    @Override
    public String nazwaOrganizmu() {
        return "Lis";
    }
}
