package pl.edu.pg.po.projekt2.zwierzeta;

import pl.edu.pg.po.projekt2.Organizm;
import pl.edu.pg.po.projekt2.Punkt;
import pl.edu.pg.po.projekt2.Swiat;
import pl.edu.pg.po.projekt2.Zwierze;

import java.util.Random;

public class Antylopa extends Zwierze {
    private static final char symbol = 'A';
    private static final int inicjatywa = 4;

    public Antylopa(Swiat swiat, Punkt pole, int turaPowstania) {
        super(swiat, pole, turaPowstania, 4, inicjatywa, symbol);
        this.sila = 4;
        this.pole = pole;
        this.swiat = swiat;
        this.umarl = false;
        this.turaPowstania = turaPowstania;
    }

    @Override
    public void akcja(Punkt pole) {
        Punkt nowePole = new Punkt(-2, -2);
        while(true) {
            nowePole = losujPunktWZasiegu(pole, 2); //specjalna akcja antylopy
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
        }
        else if(czyAntylopaMozeUciec()) { //specjalna kolizja antylopy
            Punkt nowePole = new Punkt(-2, -2);
            while(true) {
                nowePole = losujPunktWZasiegu(pole, 2);
                if (nowePole.getY() >= swiat.getY() || nowePole.getY() < 0) continue;
                else if (nowePole.getX() >= swiat.getX() || nowePole.getX() < 0) continue;
                else if (swiat.czyPoleZajete(nowePole)) continue;
                else if(nowePole.getX() == -4 && nowePole.getY() == -4) return;
                else break;
            }
            swiat.przemiescOrganizm(this, nowePole);
        }
        else {
            if(this.sila >= innyOrganizm.getSila()) {
                swiat.zabijOrganizm(innyOrganizm);
            }
        else {
                Punkt tmpPole = this.getPole();
                swiat.zabijOrganizm(this);
                swiat.przemiescOrganizm(innyOrganizm, tmpPole);
                swiat.przemiescOrganizm(innyOrganizm, this.getPole());
            }
        }
    }

    private boolean czyAntylopaMozeUciec() {
        Random rand = new Random();
        int los = rand.nextInt(100);
        if (los <= 50) return true;
        else return false;
    }

    @Override
    public String nazwaOrganizmu() {
        return "Antylopa";
    }
}
