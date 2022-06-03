package pl.edu.pg.po.projekt2.rosliny;

import pl.edu.pg.po.projekt2.*;

import java.util.Random;

public class BarszczSosnowskiego extends Roslina {
    private static final char symbol = 'B';
    private static final int inicjatywa = 0;
    private static final int sila = 10;

    public BarszczSosnowskiego(Swiat swiat, Punkt pole, int turaPowstania) {
        super(swiat, pole, turaPowstania, sila, inicjatywa, symbol);
        this.pole = pole;
        this.swiat = swiat;
        this.umarl = false;
        this.turaPowstania = turaPowstania;
    }

    @Override
    public void akcja(Punkt pole) {
        Random rand = new Random();
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
            swiat.dodajOrganizm(getTypRozmnozonegoOrganizmu(poleNowegoOrganizmu));
            Komentarz.dodajKomentarz(this.nazwaOrganizmu() + " rozprzestrzenia sie");
        }
        if(pole.getX() - 1 > 0) {
            Punkt tmpPole = new Punkt(pole.getX() - 1, pole.getY());
            if (swiat.czyPoleZajete(tmpPole) && swiat.getPole(tmpPole).nazwaOrganizmu() != "Barszcz Sosnowskiego" && swiat.getPole(tmpPole) instanceof Zwierze) {
                Komentarz.dodajKomentarz(swiat.getPole(tmpPole).nazwaOrganizmu() + " ginie od akcji barszczu");
                swiat.zabijOrganizm(swiat.getPole(tmpPole));
            }

        }
        if(pole.getY() - 1 > 0) {
            Punkt tmpPole = new Punkt(pole.getX(), pole.getY() - 1);
            if (swiat.czyPoleZajete(tmpPole) && swiat.getPole(tmpPole).nazwaOrganizmu() != "Barszcz Sosnowskiego" && swiat.getPole(tmpPole) instanceof Zwierze) {
                Komentarz.dodajKomentarz(swiat.getPole(tmpPole).nazwaOrganizmu() + " ginie od akcji barszczu");
                swiat.zabijOrganizm(swiat.getPole(tmpPole));
            }
        }
        if(pole.getX() + 1 < swiat.getX()) {
            Punkt tmpPole = new Punkt(pole.getX() + 1, pole.getY());
            if (swiat.czyPoleZajete(tmpPole) && swiat.getPole(tmpPole).nazwaOrganizmu() != "Barszcz Sosnowskiego" && swiat.getPole(tmpPole) instanceof Zwierze) {
                Komentarz.dodajKomentarz(swiat.getPole(tmpPole).nazwaOrganizmu() + " ginie od akcji barszczu");
                swiat.zabijOrganizm(swiat.getPole(tmpPole));
            }
        }
        if(pole.getY() + 1 < swiat.getY()) {
            Punkt tmpPole = new Punkt(pole.getX(), pole.getY() + 1);
            if (swiat.czyPoleZajete(tmpPole) && swiat.getPole(tmpPole).nazwaOrganizmu() != "Barszcz Sosnowskiego" && swiat.getPole(tmpPole) instanceof Zwierze) {
                Komentarz.dodajKomentarz(swiat.getPole(tmpPole).nazwaOrganizmu() + " ginie od akcji barszczu");
                swiat.zabijOrganizm(swiat.getPole(tmpPole));
            }
        }
    }

    @Override
    public void kolizja(Organizm innyOrganizm) {
        swiat.zabijOrganizm(innyOrganizm);
        swiat.zabijOrganizm(this);
        Komentarz.dodajKomentarz(innyOrganizm.nazwaOrganizmu() + " zjada " + this.nazwaOrganizmu() + " i ginie");
    }

    @Override
    public String nazwaOrganizmu() {
        return "Barszcz Sosnowskiego";
    }
}
