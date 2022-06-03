package pl.edu.pg.po.projekt2.zwierzeta;

import pl.edu.pg.po.projekt2.*;

import java.util.Random;

public class Zolw extends Zwierze {
    private static final char symbol = 'Z';
    private static final int inicjatywa = 1;

    public Zolw(Swiat swiat, Punkt pole, int turaPowstania) {
        super(swiat, pole, turaPowstania, 2, inicjatywa, symbol);
        this.sila = 2;
        this.pole = pole;
        this.swiat = swiat;
        this.umarl = false;
        this.turaPowstania = turaPowstania;
    }

    @Override
    public void akcja(Punkt pole) {
        Punkt nowePole = new Punkt(-2, -2);
        if (czyZolwMozeSieRuszyc()) { //specjalna akcja zolwia
            while(true) {
                nowePole = losujPunktWZasiegu(pole, 1);
                if (nowePole.getY() >= swiat.getY() || nowePole.getY() < 0) continue;
                else if (nowePole.getX() >= swiat.getX() || nowePole.getX() < 0) continue;
                else if(nowePole.getX() == -4 && nowePole.getY() == -4) return;
                else break;
            }
            if (swiat.czyPoleZajete(nowePole)) {
                swiat.getPole(nowePole).kolizja(this);
            }
            else {
                swiat.przemiescOrganizm(this, nowePole);
            }
        }
    }

    @Override
    public void kolizja(Organizm innyOrganizm) {
        if(innyOrganizm.nazwaOrganizmu() == this.nazwaOrganizmu() && swiat.getTura() + 1 > this.turaPowstania && swiat.getZezwolNaRozmnazanie()) {
            Punkt poleNowegoOrganizmu = new Punkt(-2, -2);
            while(true) {
                poleNowegoOrganizmu = losujPunktWZasiegu(this.getPole(), 1);
                if (poleNowegoOrganizmu.getY() >= swiat.getY() || poleNowegoOrganizmu.getY() < 0) continue;
                else if (poleNowegoOrganizmu.getX() >= swiat.getX() || poleNowegoOrganizmu.getX() < 0) continue;
                else if(poleNowegoOrganizmu.getX() == -4 && poleNowegoOrganizmu.getY() == -4) return;
                else break;
            }
            swiat.dodajOrganizm(getTypRozmnozonegoOrganizmu(poleNowegoOrganizmu));
            Komentarz.dodajKomentarz(innyOrganizm.nazwaOrganizmu() + " rozmnaza sie");
        }
        if(this.sila >= innyOrganizm.getSila()) {
            swiat.zabijOrganizm(innyOrganizm);
            Komentarz.dodajKomentarz(innyOrganizm.nazwaOrganizmu() + " atakuje " + this.nazwaOrganizmu() + " i przegrywa");
        }
        else {
            if (innyOrganizm.getSila() < 5); //specjalna kolizja zolwia
            else {
                Punkt tmpPole = this.getPole();
                swiat.zabijOrganizm(this);
                swiat.przemiescOrganizm(innyOrganizm, tmpPole);
                Komentarz.dodajKomentarz(innyOrganizm.nazwaOrganizmu() + " atakuje " + this.nazwaOrganizmu() + " i wygrywa");
            }
        }
    }

    private boolean czyZolwMozeSieRuszyc() {
        Random rand = new Random();
        int los = rand.nextInt(100);
        if (los <= 25) return true;
        else return false;
    }

    @Override
    public String nazwaOrganizmu() {
        return "Zolw";
    }
}
