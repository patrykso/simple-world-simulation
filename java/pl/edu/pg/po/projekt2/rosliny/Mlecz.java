package pl.edu.pg.po.projekt2.rosliny;

import pl.edu.pg.po.projekt2.Komentarz;
import pl.edu.pg.po.projekt2.Punkt;
import pl.edu.pg.po.projekt2.Roslina;
import pl.edu.pg.po.projekt2.Swiat;

import java.util.Random;

public class Mlecz extends Roslina {
    private static final char symbol = 'M';
    private static final int inicjatywa = 0;
    private static final int sila = 0;

    public Mlecz(Swiat swiat, Punkt pole, int turaPowstania) {
        super(swiat, pole, turaPowstania, sila, inicjatywa, symbol);
        this.pole = pole;
        this.swiat = swiat;
        this.umarl = false;
        this.turaPowstania = turaPowstania;
    }

    @Override
    public void akcja(Punkt pole) {
        Random rand = new Random();
        for(int i = 0; i < 3; i++) {
            int los = rand.nextInt(100);
            if (los <= 3 && swiat.getZezwolNaRozprzestrzenianie()) {
                Punkt poleNowegoOrganizmu = new Punkt(-2, -2);
                while(true) {
                    poleNowegoOrganizmu = losujPunktWZasiegu(this.getPole(), 1);
                    if (poleNowegoOrganizmu.getY() >= swiat.getY() || poleNowegoOrganizmu.getY() < 0) continue;
                    else if (poleNowegoOrganizmu.getX() >= swiat.getX() || poleNowegoOrganizmu.getX() < 0) continue;
                    else if(poleNowegoOrganizmu.getX() == -4 && poleNowegoOrganizmu.getY() == -4) return;
                    else break;
                }
                if(swiat.getPlansza()[poleNowegoOrganizmu.getY()][poleNowegoOrganizmu.getX()] == null) {
                    swiat.dodajOrganizm(getTypRozmnozonegoOrganizmu(poleNowegoOrganizmu));
                    Komentarz.dodajKomentarz(this.nazwaOrganizmu() + " rozprzestrzenia sie");
                }
            }
        }
    }

    @Override
    public String nazwaOrganizmu() {
        return "Mlecz";
    }
}
