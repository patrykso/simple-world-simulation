package pl.edu.pg.po.projekt2.rosliny;

import pl.edu.pg.po.projekt2.*;

public class Guarana extends Roslina {
    private static final char symbol = 'G';
    private static final int inicjatywa = 0;
    private static final int sila = 0;

    public Guarana(Swiat swiat, Punkt pole, int turaPowstania) {
        super(swiat, pole, turaPowstania, sila, inicjatywa, symbol);
        this.pole = pole;
        this.swiat = swiat;
        this.umarl = false;
        this.turaPowstania = turaPowstania;
    }

    @Override
    public void kolizja(Organizm innyOrganizm) {
        if(sila >= innyOrganizm.getSila()) {
            swiat.zabijOrganizm(this);
            swiat.zabijOrganizm(innyOrganizm);
            Komentarz.dodajKomentarz(innyOrganizm.nazwaOrganizmu() + " zjada " + this.nazwaOrganizmu() + " i ginie");
        }
        else {
            Punkt tmpPole = this.getPole();
            swiat.zabijOrganizm(this);
            swiat.przemiescOrganizm(innyOrganizm, tmpPole);
            innyOrganizm.setSila(innyOrganizm.getSila() + 3); //specjalna kolizja guarany
            Komentarz.dodajKomentarz(innyOrganizm.nazwaOrganizmu() + " zjada " + this.nazwaOrganizmu() + " i otrzymuje +3 sily");
        }
    }

    @Override
    public String nazwaOrganizmu() {
        return "Guarana";
    }
}
